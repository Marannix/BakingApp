package com.example.android.bakingapp.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Step;
import com.example.android.bakingapp.fragment.StepFragment;

import static com.example.android.bakingapp.view.RecipeStepsList.STEPS_MESSAGE;

public class StepsActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_steps);
    ButterKnife.bind(this, getViewGroup());
    Step steps = getIntent().getParcelableExtra(STEPS_MESSAGE);

    StepFragment stepFragment = new StepFragment();

    FragmentManager fragmentManager = getSupportFragmentManager();
    fragmentManager.beginTransaction()
        .add(R.id.stepsDetailLayout, stepFragment)
        .commit();
    StepFragment.newInstance(stepFragment, steps);
  }
}
