package com.abdulaala.meterzy.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class FragmentHelper {

    public FragmentHelper(FragmentTransaction fragmentTransaction) {
        this.fragmentTransaction = fragmentTransaction;
    }

    public FragmentTransaction toBackStack(String name) {
        fragmentTransaction.addToBackStack(name);
        return fragmentTransaction;
    }

    public void add(int layoutId, Fragment fragment) {
        fragmentTransaction.add(layoutId, fragment);
        fragmentTransaction.commit();
    }

    private FragmentTransaction fragmentTransaction;
}
