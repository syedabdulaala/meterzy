package com.abdulaala.meterzy.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

public class FragmentHelper {

    public FragmentHelper(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.fragmentTransaction = fragmentManager.beginTransaction();
    }

    public FragmentTransaction toBackStack(String name) {
        fragmentTransaction.addToBackStack(name);
        return fragmentTransaction;
    }

    public void add(int layoutId, Fragment fragment) {
        fragmentTransaction.add(layoutId, fragment);
        fragmentTransaction.commit();
    }

    public void replace(int layoutId, Fragment fragment) {
        fragmentTransaction.replace(layoutId, fragment);
        fragmentTransaction.commit();
    }

    public boolean hasAny() {
        List<Fragment> fragment = fragmentManager.getFragments();
        return (fragment != null && fragment.size() <= 0);
    }

    private final FragmentTransaction fragmentTransaction;
    private final FragmentManager fragmentManager;
}
