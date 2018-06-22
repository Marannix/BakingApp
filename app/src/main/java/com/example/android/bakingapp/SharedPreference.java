package com.example.android.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import com.example.android.bakingapp.data.model.Recipe;
import com.example.android.bakingapp.widget.IngredientWidgetProvider;
import com.google.gson.Gson;

public class SharedPreference {

  public static Recipe getSharedPreferences(Context context) {
    Gson gson = new Gson();
    String json = PreferenceManager.getDefaultSharedPreferences(context)
        .getString("RECIPE", "");
    return gson.fromJson(json, Recipe.class);
  }

  public static void setSharedPreferences(Context context, Recipe recipe) {
    Gson gson = new Gson();
    String json = gson.toJson(recipe);
    PreferenceManager.getDefaultSharedPreferences(context)
        .edit().putString("RECIPE", json)
        .apply();

    Intent widgetIntent = new Intent(context, IngredientWidgetProvider.class);
    widgetIntent.setAction("com.example.android.bakingapp.action.update.widget");
    int[] ids = AppWidgetManager.getInstance(context)
        .getAppWidgetIds(new ComponentName(context, IngredientWidgetProvider.class));
    widgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
    context.sendBroadcast(widgetIntent);
  }
}
