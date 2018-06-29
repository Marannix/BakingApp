package com.example.android.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.example.android.bakingapp.activity.RecipeActivity;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.SharedPreference;

/**
 * Implementation of App Widget functionality.
 */

public class IngredientWidgetProvider extends AppWidgetProvider {

  static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
    if (SharedPreference.getSharedPreferences(context) != null) {

      views.setTextViewText(R.id.recipe_title,
          SharedPreference.getSharedPreferences(context).getName());
    } else {
      views.setTextViewText(R.id.recipe_title,
          "No recipe chosen");
    }

    Intent intent = new Intent(context, IngredientWidgetService.class);
    Intent activity = new Intent(context, RecipeActivity.class);

    views.setRemoteAdapter(R.id.widgetListView, intent);

    PendingIntent pendingIntent =
        PendingIntent.getActivity(context, 0, activity, PendingIntent.FLAG_UPDATE_CURRENT);
    views.setOnClickPendingIntent(R.id.widget_layout, pendingIntent);

    appWidgetManager.updateAppWidget(appWidgetId, views);
  }

  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    // There may be multiple widgets active, so update all of them
    for (int appWidgetId : appWidgetIds) {
      updateAppWidget(context, appWidgetManager, appWidgetId);
    }
  }

  @Override public void onReceive(Context context, Intent intent) {
    super.onReceive(context, intent);

    String action = intent.getAction();
    if (action.equals("com.example.android.bakingapp.action.update.widget")) {
      AppWidgetManager appWidgetManager =
          AppWidgetManager.getInstance(context.getApplicationContext());
      ComponentName widget =
          new ComponentName(context.getApplicationContext(), IngredientWidgetProvider.class.getName());
      int[] appWidgetIds = appWidgetManager.getAppWidgetIds(widget);
      appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.recipe_title);
      onUpdate(context, appWidgetManager, appWidgetIds);
    }
  }

  @Override public void onEnabled(Context context) {
    // Enter relevant functionality for when the first widget is created
  }

  @Override public void onDisabled(Context context) {
    // Enter relevant functionality for when the last widget is disabled
  }
}

