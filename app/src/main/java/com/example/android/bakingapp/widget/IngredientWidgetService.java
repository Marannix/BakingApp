package com.example.android.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.SharedPreference;
import com.example.android.bakingapp.data.model.Ingredients;
import java.util.ArrayList;
import java.util.List;

public class IngredientWidgetService extends RemoteViewsService {

  @Override public RemoteViewsFactory onGetViewFactory(Intent intent) {
    return new MyRemoteViewsFactory(getApplicationContext());
  }

  private class MyRemoteViewsFactory implements RemoteViewsFactory {

    private List<Ingredients> ingredientsList;
    private Context context;
    private ArrayList<String> ingredients;

    public MyRemoteViewsFactory(Context context) {
      this.context = context;
      ingredients = new ArrayList<>();
    }

    @Override public void onCreate() {
      loadIngredients();
    }

    @Override public void onDataSetChanged() {
      loadIngredients();
    }

    @Override public void onDestroy() {
      if (ingredients != null) {
        ingredients.clear();
      }
    }

    @Override public int getCount() {
      return ingredients != null ? ingredients.size() : 0;
    }

    @Override public RemoteViews getViewAt(int position) {
      RemoteViews remoteViews =
          new RemoteViews(getPackageName(), R.layout.widget_ingredient_list_item);
      remoteViews.setTextViewText(R.id.ingredientWidgetText, ingredients.get(position));
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

    private void loadIngredients() {
      if (SharedPreference.getSharedPreferences(context) != null) {
        ingredientsList = SharedPreference.getSharedPreferences(context).getIngredients();
        for (Ingredients i : ingredientsList) {
          String ingredient = i.getIngredient();
          Double quantity = i.getQuantity();
          String measure = i.getMeasure();
          String ingredientString = quantity + " " + measure + " " + ingredient;
          ingredients.add(ingredientString);
        }
      } else {
        ingredients.add("No ingredients");
      }
    }
  }
}
