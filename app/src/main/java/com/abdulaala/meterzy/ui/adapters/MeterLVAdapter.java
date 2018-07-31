package com.abdulaala.meterzy.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.MeterModel;

import java.util.List;

public class MeterLVAdapter extends BaseAdapter {
    //Variable(s)
    private final Context context;
    private final MainContentCallback MainContentCallback;
    private List<MeterModel> meters;

    public MeterLVAdapter(Context context, MainContentCallback MainContentCallback, List<MeterModel> meters) {
        this.context = context;
        this.MainContentCallback = MainContentCallback;
        this.meters = meters;
    }

    @Override
    public int getCount() {
        return meters != null ? meters.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return meters != null ? meters.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return meters != null ? meters.get(i).getId() : -1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = new View(context);
        view = inflater.inflate(R.layout.item_meter, null);

        TextView tvMeter = view.findViewById(R.id.tv_meter);
        tvMeter.setText(meters.get(i).getName());

        return view;
    }
}
