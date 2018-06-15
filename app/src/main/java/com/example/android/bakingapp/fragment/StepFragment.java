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
import com.google.android.exoplayer2.util.Util;

public class StepFragment extends Fragment {

  @BindView(R.id.shortDescription) TextView shortDescription;
  @BindView(R.id.description) TextView description;
  @BindView(R.id.exoplayer) PlayerView playerView;
  @BindView(R.id.thumbnail) ImageView thumbnail;

  private static final String STEPS_LIST = "steps";

  private SimpleExoPlayer exoPlayer;
  private Step steps;
  private Uri videoUri;
  private int currentWindow;
  private long playbackPosition;
  private boolean playWhenReady = true;

  public StepFragment() {
  }

  public static StepFragment newStepInstance(Step step) {
    StepFragment stepFragment = new StepFragment();
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
    View rootView = inflater.inflate(R.layout.fragment_step, container, false);
    ButterKnife.bind(this, rootView);
    shortDescription.setText(steps.getShortDescription());
    description.setText(steps.getDescription());
    return rootView;
  }

  public void releasePlayer() {
    if (exoPlayer != null) {
      playbackPosition = exoPlayer.getCurrentPosition();
      currentWindow = exoPlayer.getCurrentWindowIndex();
      playWhenReady = exoPlayer.getPlayWhenReady();
      exoPlayer.release();
      exoPlayer = null;
    }
  }

  public void initialisePlayer() {
    initExoPlayer();
    if (!TextUtils.isEmpty(steps.getVideoURL())) {
      videoUri = Uri.parse(steps.getVideoURL());
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

  @Override public void onStart() {
    super.onStart();
    if (Util.SDK_INT > 23) {
      initialisePlayer();
    }
  }

  @Override public void onResume() {
    super.onResume();
    if ((Util.SDK_INT <= 23)) {
      initialisePlayer();
    }
  }

  @Override public void onStop() {
    super.onStop();
    if (Util.SDK_INT > 23) {
      releasePlayer();
    }
  }

  @Override public void onDestroy() {
    super.onDestroy();
    releasePlayer();
  }

  @Override public void onPause() {
    super.onPause();
    if (Util.SDK_INT <= 23) {
      releasePlayer();
    }
  }
}
