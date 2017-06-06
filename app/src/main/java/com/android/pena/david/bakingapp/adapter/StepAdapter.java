package com.android.pena.david.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.pena.david.bakingapp.Model.Step;
import com.android.pena.david.bakingapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by david on 05/06/17.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {

    public List<Step> stepsArray;
    private Context mContext;
    private final ListItemClickListener listItemClickListener;

    public interface ListItemClickListener{
        void onListItemClick(Step step);
    }

    public StepAdapter(Context context, List<Step> stepsArray, ListItemClickListener listener){
        this.stepsArray = stepsArray;
        this.mContext = context;
        listItemClickListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_row,null);
        return new StepAdapter.ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindStepData(stepsArray.get(position),position);
    }

    @Override
    public int getItemCount() {
        return stepsArray.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.n_step) TextView stepCount;
        @BindView(R.id.step_description) TextView stepDescription;
        private Step step;
        private ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        private void bindStepData(Step step, int count){
            this.step = step;
            stepCount.setText(String.valueOf(count+1));
            stepDescription.setText(step.getShortDescription());
        }

        @Override
        public void onClick(View v) {
            listItemClickListener.onListItemClick(step);
        }
    }
}
