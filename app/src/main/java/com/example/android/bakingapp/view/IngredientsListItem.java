package com.example.android.bakingapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;

public class IngredientsListItem {

  @BindView(R.id.ingredients) TextView ingredientsTextView;

  private View view;

  public IngredientsListItem(Context context, String ingredients) {
    this.view = LayoutInflater.from(context).inflate(R.layout.recipe_info_item, null, false);
    ButterKnife.bind(this, view);

    ingredientsTextView.setText(ingredients);
  }

  public View getView() {
    return view;
  }
}
