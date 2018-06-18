package com.example.android.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Ingredients;
import com.example.android.bakingapp.data.model.Recipe;
import com.example.android.bakingapp.utils.RecipeLoader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

public class IngredientWidgetService extends RemoteViewsService {
  public static final String WIDGET_EXTRA = "widget";

  @Override public RemoteViewsFactory onGetViewFactory(Intent intent) {
    int ingredientsId = intent.getIntExtra(WIDGET_EXTRA, 0);
    return new MyRemoteViewsFactory(getApplicationContext(), ingredientsId);
  }

  private class MyRemoteViewsFactory implements RemoteViewsFactory {

    private List<Ingredients> ingredientsList;
    private Context context;
    private int ingredientsId;
    private List<Recipe> recipeList;

    public MyRemoteViewsFactory(Context context, int ingredientsId) {
      this.context = context;
      this.ingredientsId = ingredientsId;
    }

    @Override public void onCreate() {

    }

    @Override public void onDataSetChanged() {
      recipeList = loadJsonFromAsset();
      ingredientsList = recipeList.get(ingredientsId).getIngredients();
    }

    @Override public void onDestroy() {

    }

    @Override public int getCount() {
      return ingredientsList != null ? ingredientsList.size() : 0;
    }

    @Override public RemoteViews getViewAt(int position) {
      RemoteViews remoteViews =
          new RemoteViews(getPackageName(), R.layout.widget_ingredient_list_item);
      remoteViews.setTextViewText(R.id.ingredientWidgetText,
          ingredientsList.get(position).getIngredient());
      return remoteViews;
    }

    @Override public RemoteViews getLoadingView() {
      return null;
    }

    @Override public int getViewTypeCount() {
      return 1;
    }

    @Override public long getItemId(int position) {
      return position;
    }

    @Override public boolean hasStableIds() {
      return false;
    }

    public List<Recipe> loadJsonFromAsset() {
      String json = RecipeLoader.loadRecipeJson(getApplicationContext());
      if (json == null) return null;
      Gson gson = new Gson();
      Type arrayOfRecipes = new TypeToken<Collection<Recipe>>() {
      }.getType();
      return gson.fromJson(json, arrayOfRecipes);
    }
  }
}
