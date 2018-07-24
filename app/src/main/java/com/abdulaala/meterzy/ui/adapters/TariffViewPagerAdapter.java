package com.abdulaala.meterzy.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.abdulaala.meterzy.ui.fragments.FixedTariffFragment;
import com.abdulaala.meterzy.ui.fragments.RangeTariffFragment;

public class TariffViewPagerAdapter extends FragmentPagerAdapter {
    private final int tabCount = 2;

    public TariffViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FixedTariffFragment();
            case 1:
                return new RangeTariffFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Fixed";
            case 1:
                return "Range";
            default:
                return null;
        }
    }
}
