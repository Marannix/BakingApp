package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Ingredients;
import java.util.List;

public class IngredientsAdapter
    extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

  private Context context;
  private List<Ingredients> ingredients;

  public IngredientsAdapter(Context context, List<Ingredients> ingredients) {
    this.context = context;
    this.ingredients = ingredients;
  }

  @NonNull @Override
  public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new IngredientsViewHolder(
        LayoutInflater.from(context).inflate(R.layout.ingredients_item, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
    Ingredients ingredient = ingredients.get(position);
    holder.ingredientsTextView.setText(formatIngredientsString(ingredient));
  }

  @Override public int getItemCount() {
    return ingredients != null ? ingredients.size() : 0;
  }

  @NonNull private String formatIngredientsString(Ingredients ingredientText) {
    return ingredientText.getQuantity()
        + " "
        + ingredientText.getMeasure()
        + " "
        + ingredientText.getIngredient();
  }

  public class IngredientsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ingredientsText) TextView ingredientsTextView;

    public IngredientsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
