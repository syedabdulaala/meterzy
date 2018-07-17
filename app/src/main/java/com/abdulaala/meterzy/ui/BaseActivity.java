package com.abdulaala.meterzy.ui;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.util.FragmentHelper;

public abstract class BaseActivity extends AppCompatActivity implements MainContentCallback {
    protected FragmentHelper fragment;
    protected AlertDialog.Builder dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = new FragmentHelper(getSupportFragmentManager());
        dialog = new AlertDialog.Builder(this);
    }

    @Override
    public void replaceMainContent(Fragment fragment) {
        if(this.fragment.hasAny())
            this.fragment.replace(R.id.fl_main, fragment);
        else
            this.fragment.add(R.id.fl_main, fragment);
    }
}
