package com.example.android.bakingapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;

public class StepsListItem {

  @BindView(R.id.stepsButton) Button stepsButton;

  private View view;

  public StepsListItem(Context context, String steps) {
    this.view = LayoutInflater.from(context).inflate(R.layout.recipe_steps_button, null, false);
    ButterKnife.bind(this, view);

    stepsButton.setText(steps);
  }

  public View getView() {
    return view;
  }
}
