package com.example.android.bakingapp.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Step;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class StepsDetailFragment extends Fragment {

  private static final String STEPS_LIST = "steps_list";
  private static final String STEPS = "steps";
  private static final String POSITION = "position";
  private static final String CURRENT_WINDOW = "current_window";
  private static final String STATE = "state";

  @BindView(R.id.shortDescription) TextView shortDescription;
  @BindView(R.id.description) TextView description;
  @BindView(R.id.exoplayer) PlayerView playerView;
  @BindView(R.id.thumbnail) ImageView thumbnail;

  private SimpleExoPlayer exoPlayer;
  private Step steps;
  private Uri videoUri;
  private int currentWindow;
  private long playbackPosition;
  private boolean playWhenReady = true;

  public StepsDetailFragment() {
  }

  public static StepsDetailFragment newStepInstance(Step step) {
    StepsDetailFragment stepFragment = new StepsDetailFragment();
    Bundle args = new Bundle();
    args.putParcelable(STEPS_LIST, step);
    stepFragment.setArguments(args);
    return stepFragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      steps = getArguments().getParcelable(STEPS_LIST);
    }
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    if (savedInstanceState != null) {
      steps = savedInstanceState.getParcelable(STEPS);
      playbackPosition = savedInstanceState.getLong(POSITION);
      currentWindow = savedInstanceState.getInt(CURRENT_WINDOW);
      playWhenReady = savedInstanceState.getBoolean(STATE);
    }

    View rootView = inflater.inflate(R.layout.fragment_step, container, false);
    ButterKnife.bind(this, rootView);
    shortDescription.setText(steps.getShortDescription());
    description.setText(steps.getDescription());
    return rootView;
  }

  @Override public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);

    if (exoPlayer != null) {
      playbackPosition = exoPlayer.getCurrentPosition();
      currentWindow = exoPlayer.getCurrentWindowIndex();
      playWhenReady = exoPlayer.getPlayWhenReady();
    }

    savedInstanceState.putParcelable(STEPS, steps);
    savedInstanceState.putLong(POSITION, playbackPosition);
    savedInstanceState.putLong(CURRENT_WINDOW, currentWindow);
    savedInstanceState.putBoolean(STATE, playWhenReady);
  }

  public void releasePlayer() {
    if (exoPlayer != null) {
      playbackPosition = exoPlayer.getCurrentPosition();
      currentWindow = exoPlayer.getCurrentWindowIndex();
      playWhenReady = exoPlayer.getPlayWhenReady();
      exoPlayer.stop();
      exoPlayer.release();
      exoPlayer = null;
    }
  }

  public void initialisePlayer(String videoURL) {
    initExoPlayer();
    if (!TextUtils.isEmpty(videoURL)) {
      videoUri = Uri.parse(videoURL);
      MediaSource mediaSource =
          new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("BakingApp")).
              createMediaSource(videoUri);
      exoPlayer.prepare(mediaSource, true, false);
    } else {
      showNoVideoError();
    }
  }

  private void initExoPlayer() {
    if (exoPlayer == null) {
      exoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
          new DefaultTrackSelector(), new DefaultLoadControl());
      showExoPlayer();
      playerView.setPlayer(exoPlayer);
      exoPlayer.setPlayWhenReady(playWhenReady);
      exoPlayer.seekTo(currentWindow, playbackPosition);
    }
  }

  private void showNoVideoError() {
    thumbnail.setVisibility(View.VISIBLE);
    playerView.setVisibility(View.INVISIBLE);
  }

  public void showExoPlayer() {
    thumbnail.setVisibility(View.INVISIBLE);
    playerView.setVisibility(View.VISIBLE);
  }

  public void updateSteps(Step step) {
    steps = step;
    releasePlayer();
    shortDescription.setText(step.getShortDescription());
    description.setText(step.getDescription());
    initialisePlayer(step.getVideoURL());
  }

  @Override public void onResume() {
    super.onResume();
    if (steps.getVideoURL() != null) {
      initialisePlayer(steps.getVideoURL());
    }
  }

  @Override public void onPause() {
    super.onPause();
    if (steps.getVideoURL() != null) {
      releasePlayer();
    }
  }
}
