package com.example.android.bakingapp.view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activity.StepsActivity;
import com.example.android.bakingapp.data.model.Step;
import java.util.List;

public class RecipeStepsList {

  @BindView(R.id.stepsButtonLayout) LinearLayout stepsButtonLayout;

  public static final String STEPS_MESSAGE = "steps";
  private StepsListItem item;
  private ViewGroup parent;
  private Context context;
  private List<Step> steps;

  public RecipeStepsList(ViewGroup parent, List<Step> steps, Context context) {
    ButterKnife.bind(this, parent);
    this.steps = steps;
    this.context = context;
    this.parent = parent;
    setStepsButtons();
  }

  private void setStepsButtons() {
    for (int i = 0; i < steps.size(); i++) {
      final int count = i;
      String stepsText = "Step " + Integer.toString(i);
      item = new StepsListItem(context, stepsText);
      stepsButtonLayout.addView(item.getView());

      item.stepsButton.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          final Intent intent = new Intent(context, StepsActivity.class);
          intent.putExtra(STEPS_MESSAGE, steps.get(count));
          context.startActivity(intent);
        }
      });
    }
  }
}
