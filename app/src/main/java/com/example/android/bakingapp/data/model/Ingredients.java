package com.example.android.bakingapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredients implements Parcelable {
  private double quantity;
  private String measure;
  private String ingredient;

  public Ingredients() {
  }

  public double getQuantity() {
    return quantity;
  }

  public String getMeasure() {
    return measure;
  }

  public String getIngredient() {
    return ingredient;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

  public void setMeasure(String measure) {
    this.measure = measure;
  }

  public void setIngredient(String ingredient) {
    this.ingredient = ingredient;
  }

  protected Ingredients(Parcel in) {
    quantity = in.readDouble();
    measure = in.readString();
    ingredient = in.readString();
  }

  public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
    @Override public Ingredients createFromParcel(Parcel in) {
      return new Ingredients(in);
    }

    @Override public Ingredients[] newArray(int size) {
      return new Ingredients[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeDouble(quantity);
    dest.writeString(measure);
    dest.writeString(ingredient);
  }
}
