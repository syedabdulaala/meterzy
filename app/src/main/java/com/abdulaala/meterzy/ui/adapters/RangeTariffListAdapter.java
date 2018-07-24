package com.abdulaala.meterzy.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.models.RangeTariffItemModel;
import com.abdulaala.meterzy.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class RangeTariffListAdapter extends BaseAdapter {
    private final Context context;
    private final boolean editMode;
    private List<RangeTariffItemModel> rangeTariffs;

    public RangeTariffListAdapter(Context context, List<RangeTariffItemModel> data, boolean editMode)
    {
        this.context = context;
        this.rangeTariffs = data;
        this.editMode = editMode;
    }

    @Override
    public int getCount() {
        return rangeTariffs != null ? rangeTariffs.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return rangeTariffs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return rangeTariffs.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = new View(context);
        view = inflater.inflate(R.layout.item_range_tariff, null);

        EditText etName = view.findViewById(R.id.et_name);
        EditText etStartRange = view.findViewById(R.id.et_start_range);
        EditText etEndRange = view.findViewById(R.id.et_end_range);
        EditText etCharges = view.findViewById(R.id.et_charges);
        RadioButton rbFixedCharges = view.findViewById(R.id.rb_fixed_charges);
        RadioButton rbPercentCharges = view.findViewById(R.id.rb_percent_charges);

        RangeTariffItemModel rangeTariff = rangeTariffs.get(i);
        if(editMode)
        {
            etName.setText(rangeTariff.getName());
            etStartRange.setText(String.valueOf(rangeTariff.getStartRange()));
            etEndRange.setText(String.valueOf(rangeTariff.getEndRange()));
            etCharges.setText(String.valueOf(rangeTariff.getCharges()));
            if(rangeTariff.getType() == Constant.TariffType.Fixed) {
                rbFixedCharges.setChecked(true);
                rbPercentCharges.setChecked(false);
            }
            else {
                rbPercentCharges.setChecked(true);
                rbFixedCharges.setChecked(false);
            }
        }
        else {
            rangeTariff.setId(0);
            rangeTariff.setName(etName.getText().toString());
            rangeTariff.setStartRange(Float.parseFloat(etStartRange.getText().toString()));
            rangeTariff.setEndRange(Float.parseFloat(etEndRange.getText().toString()));
            rangeTariff.setCharges(Float.parseFloat(etCharges.getText().toString()));
            if(rbFixedCharges.isChecked())
                rangeTariff.setType(Constant.TariffType.Fixed);
            else
                rangeTariff.setType(Constant.TariffType.Percent);
        }

        return view;
    }

    public void addNew() {
        if(rangeTariffs == null)
            rangeTariffs = new ArrayList<>();
        rangeTariffs.add(new RangeTariffItemModel());
        notifyDataSetChanged();
    }

    public List<RangeTariffItemModel> getData() {
        notifyDataSetChanged();
        return rangeTariffs;
    }
}
