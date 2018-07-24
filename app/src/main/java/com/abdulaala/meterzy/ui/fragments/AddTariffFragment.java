package com.abdulaala.meterzy.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.adapters.TariffViewPagerAdapter;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;

public class AddTariffFragment extends Fragment {
    public AddTariffFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_tariff, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabLayout tlTariff = view.findViewById(R.id.tl_tariff);
        ViewPager vpTariff = view.findViewById(R.id.vp_tariff);
        vpTariff.setAdapter(new TariffViewPagerAdapter(getActivity().getSupportFragmentManager()));
        tlTariff.setupWithViewPager(vpTariff);

        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
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

    private MainContentCallback mainContentCallback;
}
