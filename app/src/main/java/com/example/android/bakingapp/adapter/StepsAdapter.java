package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.activity.StepsActivity;
import com.example.android.bakingapp.data.model.Step;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewholder> {

  public static final String EXTRA_STEP_INFORMATION = "steps";
  public static final String EXTRA_RECIPE_NAME = "recipe_name";

  private Context context;
  private List<Step> steps;
  private String recipeName;
  private boolean isTablet;
  private int stepPosition;

  public StepsAdapter(Context context, List<Step> steps, String recipeName, boolean isTablet) {
    this.context = context;
    this.steps = steps;
    this.recipeName = recipeName;
    this.isTablet = isTablet;
  }

  @NonNull @Override
  public StepsAdapter.StepsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new StepsViewholder(
        LayoutInflater.from(context).inflate(R.layout.steps_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull StepsAdapter.StepsViewholder holder, int position) {
    final Step step = steps.get(position);
    stepPosition = position;
    holder.id.setText(Integer.toString(step.getId()));
    holder.shortDescription.setText(step.getShortDescription());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (!isTablet) {
          Intent intent = new Intent(context, StepsActivity.class);
          intent.putExtra(EXTRA_STEP_INFORMATION, step);
          intent.putExtra(EXTRA_RECIPE_NAME, recipeName);
          context.startActivity(intent);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return steps != null ? steps.size() : 0;
  }

  public int getPosition() {
    return stepPosition;
  }

  public class StepsViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.stepId) AppCompatTextView id;
    @BindView(R.id.stepShortDescription) AppCompatTextView shortDescription;

    public StepsViewholder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

}
