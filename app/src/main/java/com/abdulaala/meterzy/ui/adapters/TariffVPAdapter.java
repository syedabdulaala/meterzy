package com.abdulaala.meterzy.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.abdulaala.meterzy.ui.fragments.FixedTariffFragment;
import com.abdulaala.meterzy.ui.fragments.RangeTariffFragment;
import com.abdulaala.meterzy.ui.models.FixedTariffModel;
import com.abdulaala.meterzy.ui.models.RangeTariffModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TariffVPAdapter extends FragmentStatePagerAdapter {
    //Variable(s)
    private Map<Integer, Fragment> fragmentMap;

    //Constructor(s)
    public TariffVPAdapter(FragmentManager fm, int tariffId) {
        super(fm);
        fragmentMap = new HashMap<>();

        FixedTariffFragment fixedTariffFragment = new FixedTariffFragment();
        fixedTariffFragment.setTariffId(tariffId);
        fragmentMap.put(0, fixedTariffFragment);

        RangeTariffFragment rangeTariffFragment = new RangeTariffFragment();
        rangeTariffFragment.setTariffId(tariffId);
        fragmentMap.put(1, rangeTariffFragment);
    }

    //Function(s)
    @Override
    public Fragment getItem(int position) {
        return getByTabPosition(position);
    }

    @Override
    public int getCount() {
        return fragmentMap.size();
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

    public Fragment getByTabPosition(int id) {
        return fragmentMap.get(id);
    }

    public List<FixedTariffModel> getFixedTariffs() {
        return ((FixedTariffFragment) fragmentMap.get(0)).getFixedTariffs();
    }

    public List<RangeTariffModel> getRangeTariffs() {
        return ((RangeTariffFragment) fragmentMap.get(1)).getRangeTariffs();
    }
}
