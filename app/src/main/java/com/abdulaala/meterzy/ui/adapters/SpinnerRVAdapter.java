package com.abdulaala.meterzy.ui.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.callbacks.OnRvItemClickListener;
import com.abdulaala.meterzy.ui.models.SpinnerModel;

import java.util.List;

public class SpinnerRVAdapter extends RecyclerView.Adapter<SpinnerRVAdapter.ViewHolder> {
    //Variable(s)
    private final Context context;
    private final List<SpinnerModel> dataSet;
    private final OnRvItemClickListener listener;

    //Constructor(s)
    public SpinnerRVAdapter(Context context, List<SpinnerModel> dataSet, OnRvItemClickListener listener) {
        this.context = context;
        this.dataSet = dataSet;
        this.listener = listener;
    }

    //Function(s)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_spinner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(dataSet.get(position).getName());
        if (dataSet.get(position).isSelected())
            holder.select();
        else
            holder.deselect();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    //Nested class(es)
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            initListeners();
        }

        public void select() {
            itemView.setBackground(context.getDrawable(R.drawable.bg_spnr_dialog_item_selected));
        }

        public void deselect() {
            itemView.setBackground(new ColorDrawable(context.getColor(R.color.alabaster)));
        }

        private void initListeners() {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < dataSet.size(); i++) {
                        if (i == getAdapterPosition()) {
                            dataSet.get(i).setSelected(true);
                            select();
                        }
                        else {
                            dataSet.get(i).setSelected(false);
                            deselect();
                        }
                    }
                    listener.OnClick(getAdapterPosition(), dataSet.get(getAdapterPosition()));
                }
            });
        }
    }
}
