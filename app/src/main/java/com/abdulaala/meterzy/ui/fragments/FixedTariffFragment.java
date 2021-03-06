package com.abdulaala.meterzy.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.data.DataService;
import com.abdulaala.meterzy.data.domain.FixedTariff;
import com.abdulaala.meterzy.ui.adapters.FixedTariffRVAdapter;
import com.abdulaala.meterzy.ui.models.FixedTariffModel;

import java.util.ArrayList;
import java.util.List;

public class FixedTariffFragment extends Fragment {
    //Variable(s)
    private FixedTariffRVAdapter fixedTariffRVAdapter;
    private List<FixedTariffModel> fixedTariffs;
    private int tariffId = -1;

    //Ui Component(s)
    private RecyclerView rvFixedTariff;
    private FloatingActionButton fabAddRangeTariff;

    //Constructor(s)
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

        rvFixedTariff = view.findViewById(R.id.rv_fixed_tariff);
        fabAddRangeTariff = view.findViewById(R.id.fab_add_fixed_tariff);

        initAdapters();
        initListeners();
    }

    public void initAdapters() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFixedTariff.setLayoutManager(layoutManager);
        fixedTariffs = getFixedTariffsFromDb();
        fixedTariffRVAdapter = new FixedTariffRVAdapter(getContext(), fixedTariffs);
        rvFixedTariff.setAdapter(fixedTariffRVAdapter);
    }

    public void initListeners() {
        fabAddRangeTariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fixedTariffs.add(new FixedTariffModel());
                fixedTariffRVAdapter.notifyItemInserted(fixedTariffs.size() - 1);
                rvFixedTariff.scrollToPosition(fixedTariffs.size() - 1);
            }
        });
    }

    public List<FixedTariffModel> getFixedTariffs() {
        return fixedTariffs;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    private List<FixedTariffModel> getFixedTariffsFromDb() {
        List<FixedTariffModel> data = new ArrayList<>();

        if(tariffId != -1) {
            List<FixedTariff> fixedTariffs = DataService.getAppDb()
                    .fixedTariffRepo()
                    .getAll(tariffId);
            for (FixedTariff tariff : fixedTariffs) {
                data.add(new FixedTariffModel(tariff.getId(), tariff.getName(), tariff.getCharges(), tariff.getType()));
            }
        }

        if(data.size() == 0)
            data.add(new FixedTariffModel());
        return  data;
    }
}
