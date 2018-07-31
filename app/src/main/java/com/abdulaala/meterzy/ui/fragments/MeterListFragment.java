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
import android.widget.ImageView;
import android.widget.ListView;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.data.DataService;
import com.abdulaala.meterzy.data.domain.Meter;
import com.abdulaala.meterzy.ui.adapters.MeterLVAdapter;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.MeterModel;
import com.abdulaala.meterzy.util.BundleKey;

import java.util.ArrayList;
import java.util.List;

public class MeterListFragment extends Fragment {
    //Variable(s)
    private List<MeterModel> meters;
    private MainContentCallback mainContentCallback;

    //Ui Component(s)
    private ListView lvMeter;
    private FloatingActionButton fabAddMeter;

    //Constructor(s)
    public MeterListFragment() {
    }

    //Function(s)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meter_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvMeter = view.findViewById(R.id.lv_meter);
        fabAddMeter = view.findViewById(R.id.fab_add_meter);

        initAdapters();
        initListeners();
    }

    public void setMainContentCallback(MainContentCallback mainContentCallback) {
        this.mainContentCallback = mainContentCallback;
    }

    private void initAdapters() {
        meters = getMetersFromDb();
        lvMeter.setAdapter(new MeterLVAdapter(getContext(), mainContentCallback, meters));
    }

    private void initListeners() {
        fabAddMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMeterFragment(null);
            }
        });

        lvMeter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                loadMeterFragment(meters.get(i).getId());
            }
        });
    }

    private void loadMeterFragment(Integer id) {
        MeterFragment fragment = new MeterFragment();
        if(id != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(BundleKey.METER_ID, id);
            fragment.setArguments(bundle);
        }
        fragment.setMainContentCallback(mainContentCallback);
        mainContentCallback.replaceMainContent(fragment);
    }

    private List<MeterModel> getMetersFromDb() {
        List<Meter> meters = DataService.getAppDb()
                .meterRepo()
                .getAll();
        List<MeterModel> data = new ArrayList<>();
        for (Meter meter : meters) {
            data.add(new MeterModel(meter.getId(), meter.getTariffId(), meter.getName(),
                    meter.getAccountNo(), meter.getConsumerNo()));
        }
        return data;
    }
}
