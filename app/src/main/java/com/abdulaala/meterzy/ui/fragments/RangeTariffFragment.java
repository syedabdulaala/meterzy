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
import com.abdulaala.meterzy.data.domain.RangeTariff;
import com.abdulaala.meterzy.ui.adapters.RangeTariffRVAdapter;
import com.abdulaala.meterzy.ui.models.RangeTariffModel;

import java.util.ArrayList;
import java.util.List;

public class RangeTariffFragment extends Fragment {
    //Variable(s)
    private RangeTariffRVAdapter rangeTariffRVAdapter;
    private List<RangeTariffModel> rangeTariffs;
    private int currentTariffId = -1;

    //Ui Component(s)
    private RecyclerView rvRangeTariff;
    private FloatingActionButton fabAddRangeTariff;

    //Constructor(s)
    public RangeTariffFragment() {
    }

    //Function(s)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_range_tariff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRangeTariff = view.findViewById(R.id.rv_range_tariff);
        fabAddRangeTariff = view.findViewById(R.id.fab_add_range_tariff);

        initRvRangeTariff();
        initFabAddRangeTariff();
    }

    public void initRvRangeTariff() {
        rangeTariffs = getRangeTariffsFromDb();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRangeTariff.setLayoutManager(layoutManager);
        rangeTariffRVAdapter = new RangeTariffRVAdapter(getContext(), rangeTariffs);
        rvRangeTariff.setAdapter(rangeTariffRVAdapter);
    }

    public void initFabAddRangeTariff() {
        fabAddRangeTariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rangeTariffs.add(new RangeTariffModel());
                rangeTariffRVAdapter.notifyItemInserted(rangeTariffs.size() - 1);
                rvRangeTariff.scrollToPosition(rangeTariffs.size() - 1);
            }
        });
    }

    public List<RangeTariffModel> getRangeTariffs() {
        return rangeTariffs;
    }

    public void setCurrentTariffId(int currentTariffId) {
        this.currentTariffId = currentTariffId;
    }

    private List<RangeTariffModel> getRangeTariffsFromDb() {
        List<RangeTariff> rangeTariffs = DataService.getAppDb()
                .rangeTariffRepo()
                .getAll(currentTariffId);
        List<RangeTariffModel> data = new ArrayList<>();
        for (RangeTariff tariff : rangeTariffs) {
            data.add(new RangeTariffModel(tariff.getId(), tariff.getName(), tariff.getStartRange(),
                    tariff.getEndRange(), tariff.getCharges(), tariff.getType()));
        }
        if (data.size() == 0)
            data.add(new RangeTariffModel());
        return data;
    }
}
