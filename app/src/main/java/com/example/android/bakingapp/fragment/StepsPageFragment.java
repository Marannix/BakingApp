package com.example.android.bakingapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapter.StepsAdapter;
import com.example.android.bakingapp.data.model.Step;
import java.util.ArrayList;
import java.util.List;

public class StepsPageFragment extends Fragment {

  @BindView(R.id.stepsRecyclerView) RecyclerView recyclerView;

  private static final String TABLET = "tablet";
  private static final String RECIPE_NAME = "recipe_name";
  private static final String STEPS_LIST = "steps";

  private List<Step> steps;
  private String recipeName;
  private boolean isTablet;

  private int position;

  public StepsPageFragment() {
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null && getArguments().containsKey(STEPS_LIST)) {
      steps = getArguments().getParcelableArrayList(STEPS_LIST);
      recipeName = getArguments().getString(RECIPE_NAME);
      isTablet = getArguments().getBoolean(TABLET);
    }
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_steps_page, container, false);
    ButterKnife.bind(this, rootView);
    initStepsAdapter();
    return rootView;
  }

  private void initStepsAdapter() {
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setHasFixedSize(true);
    StepsAdapter stepsAdapter = new StepsAdapter(getContext(), steps, recipeName, isTablet);
    recyclerView.setAdapter(stepsAdapter);
    position = stepsAdapter.getPosition();
  }

  public static StepsPageFragment newStepsInstance(ArrayList<Step> steps, String name,
      boolean isTablet) {
    StepsPageFragment stepFragment = new StepsPageFragment();
    Bundle arguments = new Bundle();
    arguments.putParcelableArrayList(STEPS_LIST, steps);
    arguments.putString(RECIPE_NAME, name);
    arguments.putBoolean(TABLET, isTablet);
    stepFragment.setArguments(arguments);
    return stepFragment;
  }

  public Step getPosition() {
    return steps.get(position);
  }
}
