package com.abdulaala.meterzy.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

public class FragmentHelper {

    public FragmentHelper(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public FragmentTransaction toBackStack(String name) {
        fragmentTransaction.addToBackStack(name);
        return fragmentTransaction;
    }

    public void add(int layoutId, Fragment fragment) {
        tryInitTransaction();
        fragmentTransaction.add(layoutId, fragment);
        fragmentTransaction.commit();
        fragmentTransaction = null;
    }

    public void replace(int layoutId, Fragment fragment) {
        tryInitTransaction();
        fragmentTransaction.replace(layoutId, fragment);
        fragmentTransaction.commit();
        fragmentTransaction = null;
    }

    public boolean hasAny() {
        List<Fragment> fragment = fragmentManager.getFragments();
        return (fragment != null && fragment.size() > 0);
    }

    public FragmentTransaction addToBackStack(String name) {
        fragmentTransaction.addToBackStack(name);
        return fragmentTransaction;
    }

    public void popBackStack(String name) {
        fragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void tryInitTransaction() {
        if(this.fragmentTransaction == null)
            this.fragmentTransaction = fragmentManager.beginTransaction();
    }


    private FragmentTransaction fragmentTransaction;
    private final FragmentManager fragmentManager;
}
