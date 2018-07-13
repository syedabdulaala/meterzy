package com.abdulaala.meterzy.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.BaseActivity;
import com.abdulaala.meterzy.ui.adapters.MenuGridAdapter;
import com.abdulaala.meterzy.ui.models.MenuItemModel;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMenuGridView();
        initMenuBottomSheet();
    }

    private void initMenuGridView() {
        MenuItemModel[] menuItems = new MenuItemModel[5];
        menuItems[0] = new MenuItemModel("Meters", ContextCompat.getDrawable(this, R.drawable.ic_meter));
        menuItems[1] = new MenuItemModel("Tariffs", ContextCompat.getDrawable(this, R.drawable.ic_money));
        menuItems[2] = new MenuItemModel("Settings", ContextCompat.getDrawable(this, R.drawable.ic_setting));
        menuItems[3] = new MenuItemModel("About", ContextCompat.getDrawable(this, R.drawable.ic_info));
        menuItems[4] = new MenuItemModel("Exit", ContextCompat.getDrawable(this, R.drawable.ic_exit));

        GridView gv = findViewById(R.id.gv_menu);
        gv.setAdapter(new MenuGridAdapter(this, menuItems));
    }

    private void initMenuBottomSheet() {
        LinearLayout llMenu = findViewById(R.id.ll_menu);
        bottomSheetBehavior = BottomSheetBehavior.from(llMenu);
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.height_xl));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        //Toggle
        final ImageView ivMenuToggle = findViewById(R.id.iv_menu_toggle);
        CardView cvMenuToggle = findViewById(R.id.cv_menu_toggle);
        cvMenuToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }
        });
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_EXPANDED)
                    ivMenuToggle.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_down_arrow_a));
                else if(newState == BottomSheetBehavior.STATE_COLLAPSED)
                    ivMenuToggle.setImageDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.ic_up_arrow_a));
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    //UI Components
    private BottomSheetBehavior bottomSheetBehavior;
}
