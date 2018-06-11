package com.example.android.bakingapp.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;

public class RecipeLoader {
  @Nullable public static String loadRecipeJson(Context context) {
    String json;
    try {
      InputStream is = context.getAssets().open("BakingAppJson.json");
      int size = is.available();
      byte[] buffer = new byte[size];
      is.read(buffer);
      is.close();
      json = new String(buffer, "UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return json;
  }
}
