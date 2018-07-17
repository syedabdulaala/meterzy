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
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.MeterItemModel;

public class MeterFragment extends Fragment {
    public MeterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView lvMeter = view.findViewById(R.id.lv_meter);
        MeterItemModel[] meters = new MeterItemModel[3];
        meters[0] = new MeterItemModel(1, "Electric");
        meters[1] = new MeterItemModel(1, "Gas");
        meters[2] = new MeterItemModel(1, "Sewerage");
        lvMeter.setAdapter(new MeterListAdapter(getContext(), mainContentCallback, meters));

        FloatingActionButton fabAddMeter = view.findViewById(R.id.fab_add_meter);
        fabAddMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMeterFragment fragment = new AddMeterFragment();
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
