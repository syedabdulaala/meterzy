package com.abdulaala.meterzy.ui.activities;

import android.content.DialogInterface;
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
import com.abdulaala.meterzy.ui.adapters.MenuGVAdapter;
import com.abdulaala.meterzy.ui.fragments.MeterFragment;
import com.abdulaala.meterzy.ui.fragments.TariffListFragment;
import com.abdulaala.meterzy.ui.models.MenuModel;
import com.abdulaala.meterzy.util.Constant;

public class MainActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMenuGridView();
        initMenuBottomSheet();
    }

    private void initMenuGridView() {

        final MenuModel[] menuItems = new MenuModel[6];
        menuItems[Constant.MENU_DASHBOARD] = new MenuModel("Dashboard", ContextCompat.getDrawable(this, R.drawable.ic_dashboard));
        menuItems[Constant.MENU_METERS] = new MenuModel("Meters", ContextCompat.getDrawable(this, R.drawable.ic_meter));
        menuItems[Constant.MENU_TARIFFS] = new MenuModel("Tariffs", ContextCompat.getDrawable(this, R.drawable.ic_money));
        menuItems[Constant.MENU_SETTINGS] = new MenuModel("Setting", ContextCompat.getDrawable(this, R.drawable.ic_setting));
        menuItems[Constant.MENU_ABOUT] = new MenuModel("About", ContextCompat.getDrawable(this, R.drawable.ic_info));
        menuItems[Constant.MENU_EXIT] = new MenuModel("Exit", ContextCompat.getDrawable(this, R.drawable.ic_exit));

        GridView gv = findViewById(R.id.gv_menu);
        gv.setAdapter(new MenuGVAdapter(this, menuItems));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                changeMenu(i);
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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

    private void changeMenu(int i) {
        switch (i) {
            case Constant.MENU_METERS:
                MeterFragment meterFragment = new MeterFragment();
                meterFragment.setMainContentCallback(this);
                replaceMainContent(meterFragment);
                break;
            case Constant.MENU_TARIFFS:
                TariffListFragment tariffListFragment = new TariffListFragment();
                tariffListFragment.setMainContentCallback(this);
                replaceMainContent(tariffListFragment);
                break;
            case Constant.MENU_EXIT:
                dialog.setTitle("Exit Confirmation");
                dialog.setMessage("Are you sure you want to close application?");
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
                dialog.setNegativeButton("NO", null);
                dialog.show();
        }
    }

    //UI Components
    private BottomSheetBehavior bottomSheetBehavior;
}
