package com.example.android.bakingapp.activity;

import android.os.Bundle;
import android.util.Log;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Step;
import com.example.android.bakingapp.fragment.StepsDetailFragment;

import static com.example.android.bakingapp.adapter.StepsAdapter.EXTRA_RECIPE_NAME;
import static com.example.android.bakingapp.adapter.StepsAdapter.EXTRA_STEP_INFORMATION;

public class StepsDetailActivity extends BaseActivity {

  private static final String SAVED_POSITION = "position";
  private static final String SAVED_CURRENT_WINDOW = "current_window";
  private static final String SAVED_STATE = "state";

  private Step steps;
  private String recipeName;
  private StepsDetailFragment stepFragment;
  private long playbackPosition;
  private int currentWindow;
  private boolean playWhenReady = true;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_steps);
    ButterKnife.bind(this, getViewGroup());
    steps = getIntent().getParcelableExtra(EXTRA_STEP_INFORMATION);

    if (savedInstanceState != null) {
      playbackPosition = savedInstanceState.getLong(SAVED_POSITION);
      currentWindow = savedInstanceState.getInt(SAVED_CURRENT_WINDOW);
      playWhenReady = savedInstanceState.getBoolean(SAVED_STATE);
    }

    Log.d("Joshua", "StepsDetailActivity onCreate: " + this);
    stepFragment =
        StepsDetailFragment.newStepInstance(steps, playbackPosition, currentWindow, playWhenReady,
            false);
    getSupportFragmentManager().beginTransaction().replace(R.id.root_layout, stepFragment).commit();
    recipeName = getIntent().getStringExtra(EXTRA_RECIPE_NAME);

    setTitle(recipeName);
  }

  @Override protected void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);
    savedInstanceState.putLong(SAVED_POSITION, playbackPosition);
    savedInstanceState.putLong(SAVED_CURRENT_WINDOW, currentWindow);
    savedInstanceState.putBoolean(SAVED_STATE, playWhenReady);
  }

  public void onFragmentDestroy(Long playbackPosition, int currentWindow, boolean playWhenReady) {
    this.playbackPosition = playbackPosition;
    this.currentWindow = currentWindow;
    this.playWhenReady = playWhenReady;
  }
}
