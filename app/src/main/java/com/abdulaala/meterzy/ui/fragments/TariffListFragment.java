package com.abdulaala.meterzy.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.data.DataService;
import com.abdulaala.meterzy.data.domain.Tariff;
import com.abdulaala.meterzy.ui.adapters.TariffLVAdapter;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.TariffModel;
import com.abdulaala.meterzy.util.BundleKey;

import java.util.ArrayList;
import java.util.List;

public class TariffListFragment extends Fragment {
    //Variable(s)
    private MainContentCallback mainContentCallback;
    private TariffLVAdapter tariffLvAdapter;
    private List<TariffModel> tariffs;

    //Ui Component(s)
    private ListView lvTariff;
    FloatingActionButton fabAddMeter;

    //Constructor(s)
    public TariffListFragment() {
    }

    //Function(s)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tariff_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvTariff = view.findViewById(R.id.lv_tariff);
        fabAddMeter = view.findViewById(R.id.fab_add_tariff);

        initUiComponents();
        initListeners();
    }


    public void setMainContentCallback(MainContentCallback mainContentCallback) {
        this.mainContentCallback = mainContentCallback;
    }

    private void initUiComponents() {
        tariffs = getTariffFromDb();
        tariffLvAdapter = new TariffLVAdapter(getContext(), mainContentCallback, tariffs);
        lvTariff.setAdapter(tariffLvAdapter);
    }

    private void initListeners() {
        fabAddMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTariffFragment(null);
            }
        });

        lvTariff.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loadTariffFragment(tariffs.get(i).getId());
            }
        });
    }

    private List<TariffModel> getTariffFromDb() {
        List<Tariff> tariffs = DataService.getAppDb()
                .tariffRepo()
                .getAll();
        List<TariffModel> data = new ArrayList<>();
        for (Tariff tariff : tariffs) {
            data.add(new TariffModel(tariff.getId(), tariff.getName(),tariff.getCurrency()));
        }
        return data;
    }

    private void loadTariffFragment(Integer tariffId) {
        TariffFragment fragment = new TariffFragment();
        if (tariffId != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(BundleKey.TARIFF_ID, tariffId);
            fragment.setArguments(bundle);
        }
        fragment.setMainContentCallback(mainContentCallback);
        mainContentCallback.replaceMainContent(fragment);
    }
}
