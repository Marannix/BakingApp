package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activity.RecipePagerActivity;
import com.example.android.bakingapp.data.model.Recipe;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

  // TODO: Remove context if not needed
  private List<Recipe> recipes;
  private Context context;
  public static final String EXTRA_MESSAGE = "Recipes";
  public void setRecipeData(List<Recipe> recipes, Context context) {
    this.recipes = recipes;
    this.context = context;
  }

  @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //TODO: Get some random images for the recipes lol
    final Recipe recipe = recipes.get(position);
    holder.recipeName.setText(recipe.getName());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(context, RecipePagerActivity.class);
        intent.putExtra(EXTRA_MESSAGE, recipe);
        context.startActivity(intent);
      }
    });
  }

  @Override public int getItemCount() {
    return recipes == null ? 0 : recipes.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.recipeName) TextView recipeName;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
