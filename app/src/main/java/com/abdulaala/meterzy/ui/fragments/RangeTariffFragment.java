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

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.adapters.RangeTariffListAdapter;
import com.abdulaala.meterzy.ui.models.RangeTariffItemModel;
import com.abdulaala.meterzy.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class RangeTariffFragment extends Fragment {
    public RangeTariffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_range_tariff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ListView lvRangeTariff = view.findViewById(R.id.lv_range_tariff);
        final RangeTariffListAdapter rangeTariffListAdapter = new RangeTariffListAdapter(getContext(), null, true);
        lvRangeTariff.setAdapter(rangeTariffListAdapter);

        FloatingActionButton fabAddRangeTariff = view.findViewById(R.id.fab_add_range_tariff);
        fabAddRangeTariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rangeTariffListAdapter.addNew();
                lvRangeTariff.setSelection(rangeTariffListAdapter.getData().size() - 1);
            }
        });
    }
}
