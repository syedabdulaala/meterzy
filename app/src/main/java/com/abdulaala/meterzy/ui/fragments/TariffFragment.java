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
import com.abdulaala.meterzy.ui.adapters.MeterListAdapter;
import com.abdulaala.meterzy.ui.adapters.TariffListAdapter;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.MeterItemModel;
import com.abdulaala.meterzy.ui.models.TariffItemModel;

public class TariffFragment extends Fragment {
    public TariffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tariff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lvTariff = view.findViewById(R.id.lv_tariff);
        lvTariff.setAdapter(new TariffListAdapter(getContext(), mainContentCallback, null));

        FloatingActionButton fabAddMeter = view.findViewById(R.id.fab_add_tariff);
        fabAddMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTariffFragment fragment = new AddTariffFragment();
                fragment.setMainContentCallback(mainContentCallback);
                mainContentCallback.replaceMainContent(fragment);
            }
        });
    }

    public void setMainContentCallback(MainContentCallback mainContentCallback) {
        this.mainContentCallback = mainContentCallback;
    }

    private MainContentCallback mainContentCallback;
}
