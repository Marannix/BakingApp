package com.example.android.bakingapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Ingredients;
import com.example.android.bakingapp.data.model.Recipe;

public class RecipeDetailView {

  @BindView(R.id.ingredientsLayout) LinearLayout ingredientsLayout;
  @BindView(R.id.servingsText) TextView servings;

  private Context context;
  private Recipe recipe;

  public RecipeDetailView(ViewGroup parent, Recipe recipe, Context context) {
    ButterKnife.bind(this, parent);
    this.recipe = recipe;
    this.context = context;
    setIngredients();
    setServings();
  }

  private void setServings() {
    // TODO: Extract string to resource, user string.formant
    servings.setText(Integer.toString(recipe.getServings()) + " Servings");
  }

  private void setIngredients() {
    for (int i = 0; i < recipe.getIngredients().size(); i++) {
      String ingredientsText = formatIngredientsString(i);
      IngredientsListItem item = new IngredientsListItem(context, ingredientsText);
      ingredientsLayout.addView(item.getView());
    }
  }

  @NonNull private String formatIngredientsString(int i) {
    Ingredients ingredients = recipe.getIngredients().get(i);
    return ingredients.getQuantity()
        + " "
        + ingredients.getMeasure()
        + " "
        + ingredients.getIngredient();
  }
}
