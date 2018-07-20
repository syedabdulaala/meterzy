package com.abdulaala.meterzy.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdulaala.meterzy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvanceTariffFragment extends Fragment {
    public AdvanceTariffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_advance_tariff, container, false);
    }

}
