package com.example.android.bakingapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Step;

public class StepFragment extends Fragment {

  @BindView(R.id.shortDescription) TextView shortDescription;
  private static final String STEPS_LIST = "steps";

  private Step steps;

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
    return rootView;
  }
}
