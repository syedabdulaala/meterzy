package com.abdulaala.meterzy.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.TariffModel;

import java.util.List;

public class TariffLVAdapter extends BaseAdapter {
    private final Context context;
    private final MainContentCallback MainContentCallback;
    private List<TariffModel> tariffs;

    public TariffLVAdapter(Context context, MainContentCallback mainContentCallback, List<TariffModel> tariffs) {
        this.context = context;
        this.MainContentCallback = mainContentCallback;
        this.tariffs = tariffs;
    }

    @Override
    public int getCount() {
        return tariffs != null ? tariffs.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return tariffs != null ? tariffs.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return tariffs != null ? tariffs.get(i).getId() : 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = new View(context);
        view = inflater.inflate(R.layout.item_tariff, null);

        TextView tvTariff = view.findViewById(R.id.tv_tariff);
        tvTariff.setText(tariffs.get(i).getName());

        return view;
    }
}
