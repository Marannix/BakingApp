package com.example.android.bakingapp.fragment;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activity.RecipePagerActivity;
import com.example.android.bakingapp.activity.StepsDetailActivity;
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
  private static final String SAVED_POSITION = "position";
  private static final String SAVED_CURRENT_WINDOW = "current_window";
  private static final String SAVED_STATE = "state";
  private static final String IS_TABLET = "is_tablet";

  @BindView(R.id.shortDescription) TextView shortDescription;
  @BindView(R.id.description) TextView description;
  @BindView(R.id.exoplayer) PlayerView playerView;
  @BindView(R.id.thumbnail) ImageView thumbnail;

  private SimpleExoPlayer exoPlayer;
  private Step steps;
  private Uri videoUri;
  private int currentWindow;
  private Long playbackPosition;
  private boolean playWhenReady = true;
  private boolean isTablet;

  public StepsDetailFragment() {
  }

  public static StepsDetailFragment newStepInstance(Step step, long playbackPosition,
      int currentWindow, boolean playWhenReady, boolean isTablet) {
    StepsDetailFragment stepFragment = new StepsDetailFragment();
    Bundle args = new Bundle();
    args.putParcelable(STEPS_LIST, step);
    args.putLong(SAVED_POSITION, playbackPosition);
    args.putInt(SAVED_CURRENT_WINDOW, currentWindow);
    args.putBoolean(SAVED_STATE, playWhenReady);
    args.putBoolean(IS_TABLET, isTablet);
    stepFragment.setArguments(args);
    return stepFragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Log.d("Joshua", "onCreate: before getting old position, the position is " + playbackPosition);
    if (getArguments() != null) {
      steps = getArguments().getParcelable(STEPS_LIST);
      playbackPosition = getArguments().getLong(SAVED_POSITION);
      Log.d("Joshua", "onCreate: after getting old position, the position is " + playbackPosition);
      currentWindow = getArguments().getInt(SAVED_CURRENT_WINDOW);
      playWhenReady = getArguments().getBoolean(SAVED_STATE);
      isTablet = getArguments().getBoolean(IS_TABLET);
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
    Log.d("Joshua", "releasePlayer: before position is "
        + playbackPosition
        + "is exoPlayer null? : "
        + exoPlayer);
    if (exoPlayer != null) {
      playbackPosition = exoPlayer.getCurrentPosition();
      Log.d("Joshua", "releasePlayer: after position is " + playbackPosition);
      currentWindow = exoPlayer.getCurrentWindowIndex();
      playWhenReady = exoPlayer.getPlayWhenReady();

      if (isTablet) {
        saveTabletExoPlayer();
      } else {
        saveNonTabletExoPlayer();
      }

      exoPlayer.stop();
      exoPlayer.release();
      exoPlayer = null;
    }
  }

  public void initialisePlayer(String videoURL) {
    initExoPlayer();
    shouldShowFullScreen(!TextUtils.isEmpty(videoURL));
    if (!TextUtils.isEmpty(videoURL)) {
      videoUri = Uri.parse(videoURL);
      MediaSource mediaSource =
          new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("BakingApp")).
              createMediaSource(videoUri);

      exoPlayer.prepare(mediaSource, true, false);
      exoPlayer.setPlayWhenReady(playWhenReady);
      exoPlayer.seekTo(currentWindow, playbackPosition);
    } else {
      showNoVideoError();
    }
  }

  private void shouldShowFullScreen(boolean hasVideo) {
    boolean isLandscape = getActivity().getResources().getConfiguration().orientation
        == Configuration.ORIENTATION_LANDSCAPE;
    Log.d("Joshua", "shouldShowFullScreen: is landscape? " + isLandscape);
    if (isLandscape) {
      ViewGroup.LayoutParams params = playerView.getLayoutParams();
      params.width = ViewGroup.LayoutParams.MATCH_PARENT;
      Log.d("Joshua", "shouldShowFullScreen: has Video? " + hasVideo);
      params.height = hasVideo ? ViewGroup.LayoutParams.MATCH_PARENT : 0;
      playerView.setLayoutParams(params);
      ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }
  }

  private void initExoPlayer() {
    Log.d("Joshua", "initExoPlayer: before position is :"
        + playbackPosition
        + " is exoplayer null? : "
        + exoPlayer);
    if (exoPlayer == null) {
      exoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
          new DefaultTrackSelector(), new DefaultLoadControl());
      playerView.setPlayer(exoPlayer);
      showExoPlayer();
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
    Log.d("Joshua", "updateSteps - releasePlayer called");
    releasePlayer();
    shortDescription.setText(step.getShortDescription());
    description.setText(step.getDescription());
    initialisePlayer(step.getVideoURL());
  }

  public void saveTabletExoPlayer() {
    ((RecipePagerActivity) getActivity()).onFragmentDestroy(playbackPosition, currentWindow,
        playWhenReady);
  }

  public void saveNonTabletExoPlayer() {
    ((StepsDetailActivity) getActivity()).onFragmentDestroy(playbackPosition, currentWindow,
        playWhenReady);
  }

  @Override public void onResume() {
    super.onResume();
    if (steps.getVideoURL() != null) {
      initialisePlayer(steps.getVideoURL());
    }
  }

  @Override public void onPause() {
    super.onPause();
    Log.d("Joshua", "onPause is called ");
    if (steps.getVideoURL() != null) {
      releasePlayer();
    }
  }
}
