package com.example.android.bakingapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.model.Step;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewholder> {

  private Context context;
  private List<Step> steps;

  public StepsAdapter(Context context, List<Step> steps) {
    this.context = context;
    this.steps = steps;
  }

  @NonNull @Override
  public StepsAdapter.StepsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new StepsViewholder(
        LayoutInflater.from(context).inflate(R.layout.steps_item, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull StepsAdapter.StepsViewholder holder, int position) {
    Step step = steps.get(position);
    holder.id.setText(Integer.toString(step.getId()));
    holder.shortDescription.setText(step.getShortDescription());
  }

  @Override public int getItemCount() {
    return steps != null ? steps.size() : 0;
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
