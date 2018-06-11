package com.example.android.bakingapp.data.model;

public class Ingredients {
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
}
