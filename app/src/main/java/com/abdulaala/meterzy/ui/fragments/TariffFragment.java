package com.abdulaala.meterzy.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.data.DataService;
import com.abdulaala.meterzy.data.domain.FixedTariff;
import com.abdulaala.meterzy.data.domain.RangeTariff;
import com.abdulaala.meterzy.data.domain.Tariff;
import com.abdulaala.meterzy.ui.adapters.TariffVPAdapter;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.FixedTariffModel;
import com.abdulaala.meterzy.ui.models.RangeTariffModel;

public class TariffFragment extends Fragment {
    //Variable(s)
    private MainContentCallback mainContentCallback;
    private TariffVPAdapter vpTariffAdapter;

    //Ui Component(s)
    private TabLayout tlTariff;
    private ViewPager vpTariff;
    private EditText etName;
    private EditText etCurrency;
    private Button btnCancel;
    private Button btnSave;

    //Constructor(s)
    public TariffFragment() {
    }

    //Function(s)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tariff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tlTariff = view.findViewById(R.id.tl_tariff);
        vpTariff = view.findViewById(R.id.vp_tariff);
        etName = view.findViewById(R.id.et_name);
        etCurrency = view.findViewById(R.id.et_currency);
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnSave = view.findViewById(R.id.btn_save);

        initTab();
        initBtnCancel();
        initBtnSave();
    }

    private void initTab() {
        vpTariffAdapter = new TariffVPAdapter(getActivity().getSupportFragmentManager());
        vpTariff.setAdapter(vpTariffAdapter);
        tlTariff.setupWithViewPager(vpTariff);
    }

    private void initBtnCancel() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTariffFragment();
            }
        });
    }

    private void initBtnSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTariffFragment();
                saveToDb();
                Toast.makeText(getContext(),"Saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setMainContentCallback(MainContentCallback mainContentCallback) {
        this.mainContentCallback = mainContentCallback;
    }

    private void loadTariffFragment() {
        TariffListFragment fragment = new TariffListFragment();
        fragment.setMainContentCallback(mainContentCallback);
        mainContentCallback.replaceMainContent(fragment);
    }

    private void saveToDb() {
        int tariffId = (int) DataService.getAppDb().tariffRepo()
                .insert(new Tariff(0, etName.getText().toString(), etCurrency.getText().toString()));

        for(RangeTariffModel tariff : vpTariffAdapter.getRangeTariffs()) {
            DataService.getAppDb().rangeTariffRepo()
                    .insert(new RangeTariff(0,tariffId,tariff.getStartRange(),tariff.getEndRange(),
                            tariff.getCharges(), tariff.getName(), tariff.getType()));
        }

        for (FixedTariffModel tariff : vpTariffAdapter.getFixedTariffs()) {
            DataService.getAppDb().fixedTariffRepo()
                    .insert(new FixedTariff(0, tariffId, tariff.getCharges(), tariff.getName(), tariff.getType()));
        }
    }
}
