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

public class MeterLVAdapter extends BaseAdapter {
    public MeterLVAdapter(Context context, MainContentCallback MainContentCallback, MeterModel[] meters) {
        this.context = context;
        this.MainContentCallback = MainContentCallback;
        this.meters = meters;
    }

    @Override
    public int getCount() {
        return meters.length;
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
        view = inflater.inflate(R.layout.item_meter, null);

        TextView tvMeter = view.findViewById(R.id.tv_meter);
        tvMeter.setText(meters[i].getName());
        ImageButton ibMeterReading = view.findViewById(R.id.ib_meter_reading);
        ibMeterReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "This will show readings", Toast.LENGTH_SHORT);
            }
        });

        return view;
    }

    private final Context context;
    private final MainContentCallback MainContentCallback;
    private MeterModel[] meters;
}
