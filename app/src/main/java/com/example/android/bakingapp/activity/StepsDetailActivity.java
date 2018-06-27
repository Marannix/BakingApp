package com.example.android.bakingapp.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Step;
import com.example.android.bakingapp.fragment.StepsDetailFragment;

import static com.example.android.bakingapp.adapter.StepsAdapter.EXTRA_RECIPE_NAME;
import static com.example.android.bakingapp.adapter.StepsAdapter.EXTRA_STEP_INFORMATION;

public class StepsDetailActivity extends BaseActivity {

  private static final String STEPS = "steps";

  private Step steps;
  private String recipeName;
  private StepsDetailFragment stepFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_steps);
    ButterKnife.bind(this, getViewGroup());

    if (savedInstanceState != null) {
      steps = savedInstanceState.getParcelable(STEPS);
    } else {
      steps = getIntent().getParcelableExtra(EXTRA_STEP_INFORMATION);
    }

    recipeName = getIntent().getStringExtra(EXTRA_RECIPE_NAME);
    stepFragment = StepsDetailFragment.newStepInstance(steps);
    getSupportFragmentManager().beginTransaction().add(R.id.root_layout, stepFragment).commit();
    setTitle(recipeName);
  }

  @Override protected void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);
    steps = savedInstanceState.getParcelable(STEPS);
  }
}
