package com.example.android.bakingapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.android.bakingapp.data.model.Ingredients;
import java.util.List;

@Dao public interface IngredientsDao {

  @Query("SELECT * FROM ingredients where widgetId = :widgetId") List<Ingredients> getAllIngredients(int widgetId);

  @Insert(onConflict = OnConflictStrategy.REPLACE) void insert(List<Ingredients> ingredients);
}
