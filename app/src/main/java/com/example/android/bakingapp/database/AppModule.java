package com.example.android.bakingapp.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class AppModule {

  private static RecipeDatabase INSTANCE;

  public static RecipeDatabase getAppDatabase(Context context) {
    if (INSTANCE == null) {
      INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RecipeDatabase.class, "l").allowMainThreadQueries().build();
    }

    return INSTANCE;
  }
}
