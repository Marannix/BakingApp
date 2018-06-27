package com.example.android.bakingapp.activity;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Step;
import com.example.android.bakingapp.fragment.StepsDetailFragment;

import static com.example.android.bakingapp.adapter.StepsAdapter.EXTRA_RECIPE_NAME;
import static com.example.android.bakingapp.adapter.StepsAdapter.EXTRA_STEP_INFORMATION;

public class StepsDetailActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_steps);
    ButterKnife.bind(this, getViewGroup());
    Step steps = getIntent().getParcelableExtra(EXTRA_STEP_INFORMATION);
    String recipeName = getIntent().getStringExtra(EXTRA_RECIPE_NAME);
    StepsDetailFragment stepFragment = StepsDetailFragment.newStepInstance(steps);
    getSupportFragmentManager().beginTransaction().add(R.id.root_layout, stepFragment).commit();
    setTitle(recipeName);
  }
}
