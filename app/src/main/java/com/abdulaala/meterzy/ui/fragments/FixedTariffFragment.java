package com.abdulaala.meterzy.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.adapters.FixedTariffListAdapter;
import com.abdulaala.meterzy.ui.adapters.RangeTariffListAdapter;
import com.abdulaala.meterzy.ui.models.FixedTariffItemModel;
import com.abdulaala.meterzy.ui.models.RangeTariffItemModel;
import com.abdulaala.meterzy.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class FixedTariffFragment extends Fragment {
    public FixedTariffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fixed_tariff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListView lvRangeTariff = view.findViewById(R.id.lv_fixed_tariff);
        List<FixedTariffItemModel> fixedTariffs = new ArrayList<>();
        fixedTariffs.add(new FixedTariffItemModel(0, "Electric Duty", 1.5f, Constant.TariffType.Percent));
        fixedTariffs.add(new FixedTariffItemModel(0, "GST", 17.25f, Constant.TariffType.Percent));
        fixedTariffs.add(new FixedTariffItemModel(0, "TVL License Fee", 35.0f, Constant.TariffType.Fixed));
        fixedTariffs.add(new FixedTariffItemModel(0, "Meter Rent", 7.5f, Constant.TariffType.Fixed));
        fixedTariffs.add(new FixedTariffItemModel(0, "Bank Charges", 8.0f, Constant.TariffType.Fixed));
        final FixedTariffListAdapter fixedTariffListAdapter = new FixedTariffListAdapter(getContext(), fixedTariffs, true);
        lvRangeTariff.setAdapter(fixedTariffListAdapter);

        FloatingActionButton fabAddRangeTariff = view.findViewById(R.id.fab_add_fixed_tariff);
        fabAddRangeTariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixedTariffListAdapter.addNew();
                lvRangeTariff.setSelection(fixedTariffListAdapter.getData().size() - 1);
            }
        });
    }
}
