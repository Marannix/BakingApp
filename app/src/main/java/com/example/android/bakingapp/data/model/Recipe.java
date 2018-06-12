package com.example.android.bakingapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Recipe implements Parcelable {

  private int id;
  private String name;
  private List<Ingredients> ingredients;
  private List<Step> steps;
  private int servings;
  private String image;

  public Recipe(int id, String name, List<Ingredients> ingredients, List<Step> steps, int servings,
      String image) {
    this.id = id;
    this.name = name;
    this.ingredients = ingredients;
    this.steps = steps;
    this.servings = servings;
    this.image = image;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Ingredients> getIngredients() {
    return ingredients;
  }

  public List<Step> getSteps() {
    return steps;
  }

  public int getServings() {
    return servings;
  }

  public String getImage() {
    return image;
  }

  protected Recipe(Parcel in) {
    id = in.readInt();
    name = in.readString();
    ingredients = in.createTypedArrayList(Ingredients.CREATOR);
    steps = in.createTypedArrayList(Step.CREATOR);
    servings = in.readInt();
    image = in.readString();
  }

  public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
    @Override public Recipe createFromParcel(Parcel in) {
      return new Recipe(in);
    }

    @Override public Recipe[] newArray(int size) {
      return new Recipe[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(name);
    dest.writeTypedList(ingredients);
    dest.writeTypedList(steps);
    dest.writeInt(servings);
    dest.writeString(image);
  }
}
