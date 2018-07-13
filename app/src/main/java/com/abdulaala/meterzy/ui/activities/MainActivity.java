package com.abdulaala.meterzy.ui.activities;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.BaseActivity;
import com.abdulaala.meterzy.ui.adapters.MenuGridAdapter;
import com.abdulaala.meterzy.ui.fragments.MeterFragment;
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
        final MenuItemModel[] menuItems = new MenuItemModel[6];
        menuItems[0] = new MenuItemModel("Meters", ContextCompat.getDrawable(this, R.drawable.ic_meter));
        menuItems[1] = new MenuItemModel("Readings", ContextCompat.getDrawable(this, R.drawable.ic_reading));
        menuItems[2] = new MenuItemModel("Tariffs", ContextCompat.getDrawable(this, R.drawable.ic_money));
        menuItems[3] = new MenuItemModel("Setting", ContextCompat.getDrawable(this, R.drawable.ic_setting));
        menuItems[4] = new MenuItemModel("About", ContextCompat.getDrawable(this, R.drawable.ic_info));
        menuItems[5] = new MenuItemModel("Exit", ContextCompat.getDrawable(this, R.drawable.ic_exit));

        GridView gv = findViewById(R.id.gv_menu);
        gv.setAdapter(new MenuGridAdapter(this, menuItems));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        fragment.add(R.id.fl_main, new MeterFragment());
                        break;
                }
            }
        });
    }

    private void initMenuBottomSheet() {
        LinearLayout llMenu = findViewById(R.id.ll_menu);
        bottomSheetBehavior = BottomSheetBehavior.from(llMenu);
        bottomSheetBehavior.setPeekHeight((int) getResources().getDimension(R.dimen.height_xl));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        //Toggle
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
    }

    private void showMeterFragment() {

    }

    //UI Components
    private BottomSheetBehavior bottomSheetBehavior;
}
