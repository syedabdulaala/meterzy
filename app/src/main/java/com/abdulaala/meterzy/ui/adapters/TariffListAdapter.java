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
import com.abdulaala.meterzy.ui.models.TariffItemModel;

public class TariffListAdapter extends BaseAdapter {
    public TariffListAdapter(Context context, MainContentCallback mainContentCallback, TariffItemModel[] tariffs) {
        this.context = context;
        this.MainContentCallback = mainContentCallback;
        this.tariffs = tariffs;
    }

    @Override
    public int getCount() {
        return tariffs.length;
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

        TextView tvTariff = view.findViewById(R.id.tv_tariff);
        tvTariff.setText(tariffs[i].getName());

        return view;
    }

    private final Context context;
    private final MainContentCallback MainContentCallback;
    private TariffItemModel[] tariffs;
}
