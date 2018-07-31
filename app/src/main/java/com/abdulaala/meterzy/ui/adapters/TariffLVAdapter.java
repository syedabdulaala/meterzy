package com.abdulaala.meterzy.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.data.DataService;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.TariffModel;

import java.util.List;

public class TariffLVAdapter extends BaseAdapter {
    //Variable(s)
    private final Context context;
    private final MainContentCallback MainContentCallback;
    private List<TariffModel> tariffs;
    private AlertDialog.Builder dialog;

    //Constructor(s)
    public TariffLVAdapter(Context context, MainContentCallback mainContentCallback, List<TariffModel> tariffs) {
        this.context = context;
        this.MainContentCallback = mainContentCallback;
        this.tariffs = tariffs;
        this.dialog = new AlertDialog.Builder(context);
    }

    //Function(s)
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null)
            view = new View(context);
        view = inflater.inflate(R.layout.item_tariff, null);

        TextView tvTariff = view.findViewById(R.id.tv_tariff);
        tvTariff.setText(tariffs.get(i).getName());

        ImageView ivDelete = view.findViewById(R.id.iv_delete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTariff(tariffs.get(i).getId());
            }
        });

        return view;
    }

    private void deleteTariff(final int tariffId) {
        dialog.setTitle("Confirm?");
        dialog.setMessage("Are you sure you want to delete this tariff?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataService.getAppDb()
                        .tariffRepo()
                        .delete(tariffId);
                DataService.getAppDb()
                        .fixedTariffRepo()
                        .deleteAll(tariffId);
                DataService.getAppDb()
                        .rangeTariffRepo()
                        .deleteAll(tariffId);
                notifyDataSetChanged();
            }
        });
        dialog.setNegativeButton("NO", null);
        dialog.show();
    }
}
