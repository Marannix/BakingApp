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
import com.example.android.bakingapp.adapter.IngredientsAdapter;
import com.example.android.bakingapp.data.model.Ingredients;
import java.util.ArrayList;
import java.util.List;

public class IngredientsPageFragment extends Fragment {

  @BindView(R.id.ingredientsRecyclerView) RecyclerView recyclerView;

  private static final String INGREDIENTS_LIST = "ingredients";
  private List<Ingredients> ingredients;

  public IngredientsPageFragment() {
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null && getArguments().containsKey(INGREDIENTS_LIST)) {
      ingredients = getArguments().getParcelableArrayList(INGREDIENTS_LIST);
    }
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_ingredients_page, container, false);
    ButterKnife.bind(this, rootView);
    initIngredientsAdapter();
    return rootView;
  }

  public static IngredientsPageFragment newIngredientsInstance(ArrayList<Ingredients> ingredients) {
    IngredientsPageFragment ingredientsPageFragment = new IngredientsPageFragment();
    Bundle args = new Bundle();
    args.putParcelableArrayList(INGREDIENTS_LIST, ingredients);
    ingredientsPageFragment.setArguments(args);
    return ingredientsPageFragment;
  }

  private void initIngredientsAdapter() {
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setHasFixedSize(true);
    IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(getContext(), ingredients);
    recyclerView.setAdapter(ingredientsAdapter);
  }
}
