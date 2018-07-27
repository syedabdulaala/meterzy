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
import com.abdulaala.meterzy.data.DataService;
import com.abdulaala.meterzy.data.domain.Tariff;
import com.abdulaala.meterzy.ui.adapters.TariffLVAdapter;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.TariffModel;

import java.util.ArrayList;
import java.util.List;

public class TariffListFragment extends Fragment {
    private MainContentCallback mainContentCallback;

    public TariffListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tariff_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lvTariff = view.findViewById(R.id.lv_tariff);
        lvTariff.setAdapter(new TariffLVAdapter(getContext(), mainContentCallback, getDataFromDb()));

        FloatingActionButton fabAddMeter = view.findViewById(R.id.fab_add_tariff);
        fabAddMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TariffFragment fragment = new TariffFragment();
                fragment.setMainContentCallback(mainContentCallback);
                mainContentCallback.replaceMainContent(fragment);
            }
        });
    }

    public void setMainContentCallback(MainContentCallback mainContentCallback) {
        this.mainContentCallback = mainContentCallback;
    }

    private List<TariffModel> getDataFromDb() {
        List<Tariff> tariffs = DataService.getAppDb()
                .tariffRepo()
                .getAll();
        List<TariffModel> data = new ArrayList<>();
        for (Tariff tariff : tariffs) {
            data.add(new TariffModel(tariff.getId(), tariff.getName()));
        }
        return data;
    }


}
