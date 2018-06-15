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

  public static final String STEPS_LIST = "steps";
  private List<Step> steps;

  public StepsPageFragment() {
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null && getArguments().containsKey(STEPS_LIST)) {
      steps = getArguments().getParcelableArrayList(STEPS_LIST);
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
    StepsAdapter stepsAdapter = new StepsAdapter(getContext(), steps);
    recyclerView.setAdapter(stepsAdapter);
  }

  public static StepsPageFragment newStepsInstance(ArrayList<Step> steps) {
    StepsPageFragment stepFragment = new StepsPageFragment();
    // Set the bundle arguments for the fragment.
    Bundle arguments = new Bundle();
    arguments.putParcelableArrayList(STEPS_LIST, steps);
    stepFragment.setArguments(arguments);
    return stepFragment;
  }
}
