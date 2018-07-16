package com.abdulaala.meterzy.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.adapters.MeterListAdapter;
import com.abdulaala.meterzy.ui.callbacks.ChangeMainContentCallback;
import com.abdulaala.meterzy.ui.models.MeterItemModel;

public class MeterFragment extends Fragment {


    public MeterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        lvMeter.setAdapter(new MeterListAdapter(getContext(), changeMainContentCallback, meters));
    }

    public ChangeMainContentCallback getChangeMainContentCallback() {
        return changeMainContentCallback;
    }

    public void setChangeMainContentCallback(ChangeMainContentCallback changeMainContentCallback) {
        this.changeMainContentCallback = changeMainContentCallback;
    }

    private ChangeMainContentCallback changeMainContentCallback;
}
