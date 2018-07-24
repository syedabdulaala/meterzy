package com.abdulaala.meterzy.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioButton;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.models.FixedTariffItemModel;
import com.abdulaala.meterzy.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class FixedTariffListAdapter extends BaseAdapter {
    private final Context context;
    private final boolean editMode;
    private List<FixedTariffItemModel> fixedTariffs;

    public FixedTariffListAdapter(Context context, List<FixedTariffItemModel> data, boolean editMode) {
        this.context = context;
        this.fixedTariffs = data;
        this.editMode = editMode;
    }

    @Override
    public int getCount() {
        return fixedTariffs != null ? fixedTariffs.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return fixedTariffs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return fixedTariffs.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = new View(context);
        view = inflater.inflate(R.layout.item_fixed_tariff, null);

        EditText etName = view.findViewById(R.id.et_name);
        EditText etCharges = view.findViewById(R.id.et_charges);
        RadioButton rbFixedCharges = view.findViewById(R.id.rb_fixed_charges);
        RadioButton rbPercentCharges = view.findViewById(R.id.rb_percent_charges);

        FixedTariffItemModel fixedTariff = fixedTariffs.get(i);
        if (editMode) {
            etName.setText(fixedTariff.getName());
            etCharges.setText(String.valueOf(fixedTariff.getCharges()));
            if (fixedTariff.getType() == Constant.TariffType.Fixed) {
                rbFixedCharges.setChecked(true);
                rbPercentCharges.setChecked(false);
            } else {
                rbPercentCharges.setChecked(true);
                rbFixedCharges.setChecked(false);
            }
        }
        else {
            fixedTariff.setId(0);
            fixedTariff.setName(etName.getText().toString());
            fixedTariff.setCharges(Float.parseFloat(etName.getText().toString()));
            if (rbFixedCharges.isChecked()) {
                fixedTariff.setType(Constant.TariffType.Fixed);
            } else {
                fixedTariff.setType(Constant.TariffType.Percent);
            }
        }

        return view;
    }

    public void addNew() {
        if(fixedTariffs == null)
            fixedTariffs = new ArrayList<>();
        fixedTariffs.add(new FixedTariffItemModel());
        notifyDataSetChanged();
    }

    public List<FixedTariffItemModel> getData() {
        notifyDataSetChanged();
        return fixedTariffs;
    }
}
