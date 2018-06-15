package com.example.android.bakingapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {
  private int id;
  private String shortDescription;
  private String description;
  private String videoURL;
  private String thumbnailURL;

  public Step(int id, String shortDescription, String description, String videoUrl,
      String thumbnailUrl) {
    this.id = id;
    this.shortDescription = shortDescription;
    this.description = description;
    this.videoURL = videoUrl;
    this.thumbnailURL = thumbnailUrl;
  }

  public int getId() {
    return id;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public String getDescription() {
    return description;
  }

  public String getVideoURL() {
    return videoURL;
  }

  public String getThumbnailURL() {
    return thumbnailURL;
  }

  @Override public int describeContents() {
    return 0;
  }

  protected Step(Parcel in) {
    id = in.readInt();
    shortDescription = in.readString();
    description = in.readString();
    videoURL = in.readString();
    thumbnailURL = in.readString();
  }

  public static final Creator<Step> CREATOR = new Creator<Step>() {
    @Override public Step createFromParcel(Parcel in) {
      return new Step(in);
    }

    @Override public Step[] newArray(int size) {
      return new Step[size];
    }
  };

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(shortDescription);
    dest.writeString(description);
    dest.writeString(videoURL);
    dest.writeString(thumbnailURL);
  }
}
