package com.example.android.bakingapp.fragment;

import android.os.Bundle;
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

  //private TextView shortDescription;
  @BindView(R.id.shortDescription) TextView shortDescription;

  private static final String ARG_PARAM1 = "steps";

  // TODO: Rename and change types of parameters
  private Step steps;

  //private OnFragmentInteractionListener mListener;

  public StepFragment() {
    // Required empty public constructor
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    Bundle bundle = getArguments();

    if (bundle != null) {
      steps = bundle.getParcelable(ARG_PARAM1);

    }
  }

  public static StepFragment newInstance(StepFragment stepFragment, Step step) {
    Bundle args = new Bundle();
    args.putParcelable(ARG_PARAM1, step);
    stepFragment.setArguments(args);
    return stepFragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      //mParam1 = getArguments().getParcelable(ARG_PARAM1);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    ButterKnife.bind(this, container);
    View rootView = inflater.inflate(R.layout.fragment_step, container, false);
    shortDescription.setText(steps.getShortDescription());
    return rootView;
  }

  //// TODO: Rename method, update argument and hook method into UI event
  //public void onButtonPressed(Uri uri) {
  //  if (mListener != null) {
  //    mListener.onFragmentInteraction(uri);
  //  }
  //}

  //@Override public void onAttach(Context context) {
  //  super.onAttach(context);
  //  if (context instanceof OnFragmentInteractionListener) {
  //    mListener = (OnFragmentInteractionListener) context;
  //  } else {
  //    throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
  //  }
  //}

  @Override public void onDetach() {
    super.onDetach();
    //mListener = null;
  }

  //public interface OnFragmentInteractionListener {
  //  // TODO: Update argument type and name
  //  void onFragmentInteraction(Uri uri);
  //}
}
