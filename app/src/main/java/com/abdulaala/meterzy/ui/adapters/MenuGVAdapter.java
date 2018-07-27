package com.abdulaala.meterzy.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.models.MenuModel;

public class MenuGVAdapter extends BaseAdapter {
    private Context context;
    private MenuModel[] menuItems;

    public MenuGVAdapter(Context context, MenuModel[] menuItems) {
        this.context = context;
        this.menuItems = menuItems;
    }

    @Override
    public int getCount() {
        return menuItems.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = new View(context);
        view = inflater.inflate(R.layout.item_menu, null);

        final ImageView ivMenu = view.findViewById(R.id.iv_menu);
        ivMenu.setImageDrawable(menuItems[i].getIcon());
        TextView tvMenu = view.findViewById(R.id.tv_menu);
        tvMenu.setText(menuItems[i].getName());

        return view;
    }
}
