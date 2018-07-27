package com.abdulaala.meterzy.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.data.domain.RangeTariff;
import com.abdulaala.meterzy.ui.models.FixedTariffModel;
import com.abdulaala.meterzy.ui.models.RangeTariffModel;
import com.abdulaala.meterzy.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class RangeTariffRVAdapter extends RecyclerView.Adapter<RangeTariffRVAdapter.ViewHolder> {
    //Variable(s)
    private final Context context;
    private List<RangeTariffModel> dataSet;

    //Constructor(s)
    public RangeTariffRVAdapter(Context context, List<RangeTariffModel> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    //Function(s)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_range_tariff, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(dataSet.get(position).getName() != null)
            holder.etName.setText(dataSet.get(position).getName().toString());
        if(dataSet.get(position).getCharges() != null)
            holder.etStartRange.setText(dataSet.get(position).getStartRange().toString());
        if(dataSet.get(position).getCharges() != null)
            holder.etEndRange.setText(dataSet.get(position).getEndRange().toString());
        if(dataSet.get(position).getCharges() != null)
            holder.etCharges.setText(dataSet.get(position).getCharges().toString());
        if(dataSet.get(position).getType() == Constant.TariffType.Fixed)
            holder.rbFixedCharges.setChecked(true);
        else if (dataSet.get(position).getType() == Constant.TariffType.Percent)
            holder.rbPercentCharges.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }


    //Nested class(es)
    public class ViewHolder extends RecyclerView.ViewHolder {
        public EditText etName;
        public EditText etStartRange;
        public EditText etEndRange;
        public EditText etCharges;
        public RadioButton rbFixedCharges;
        public RadioButton rbPercentCharges;
        public ImageView ivClose;

        public ViewHolder(final View itemView) {
            super(itemView);

            ivClose = itemView.findViewById(R.id.iv_close);
            etName = itemView.findViewById(R.id.et_name);
            etStartRange = itemView.findViewById(R.id.et_start_range);
            etEndRange = itemView.findViewById(R.id.et_end_range);
            etCharges = itemView.findViewById(R.id.et_charges);
            rbFixedCharges = itemView.findViewById(R.id.rb_fixed_charges);
            rbPercentCharges = itemView.findViewById(R.id.rb_percent_charges);

            initComponents();
        }

        private void initComponents() {
            ivClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataSet.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
            etName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0)
                        dataSet.get(getAdapterPosition()).setName(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            etStartRange.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0)
                        dataSet.get(getAdapterPosition()).setStartRange(Float.parseFloat(charSequence.toString()));
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            etEndRange.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0)
                        dataSet.get(getAdapterPosition()).setEndRange(Float.parseFloat(charSequence.toString()));
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            etCharges.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() > 0)
                        dataSet.get(getAdapterPosition()).setCharges(Float.parseFloat(charSequence.toString()));
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            rbFixedCharges.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b == true)
                        dataSet.get(getAdapterPosition()).setType(Constant.TariffType.Fixed);
                }
            });
            rbPercentCharges.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b == true)
                        dataSet.get(getAdapterPosition()).setType(Constant.TariffType.Percent);
                }
            });
        }
    }
}
