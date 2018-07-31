package com.abdulaala.meterzy.ui.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.data.DataService;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.models.MeterModel;

import java.util.List;

public class MeterLVAdapter extends BaseAdapter {
    //Variable(s)
    private final Context context;
    private final MainContentCallback MainContentCallback;
    private List<MeterModel> meters;
    private AlertDialog.Builder dialog;

    //Constructor(s)
    public MeterLVAdapter(Context context, MainContentCallback MainContentCallback, List<MeterModel> meters) {
        this.context = context;
        this.MainContentCallback = MainContentCallback;
        this.meters = meters;
        this.dialog = new AlertDialog.Builder(context);
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = new View(context);
        view = inflater.inflate(R.layout.item_meter, null);

        TextView tvMeter = view.findViewById(R.id.tv_meter);
        tvMeter.setText(meters.get(i).getName());

        ImageView ivDelete = view.findViewById(R.id.iv_delete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMeter(meters.get(i).getId());
            }
        });

        return view;
    }

    private void deleteMeter(final int meterId) {
        dialog.setTitle("Confirm?");
        dialog.setMessage("Are you sure you want to delete this meter?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataService.getAppDb()
                        .meterRepo()
                        .delete(meterId);
                DataService.getAppDb()
                        .meterReadingRepo()
                        .deleteAll(meterId);
                notifyDataSetChanged();
            }
        });
        dialog.setNegativeButton("NO", null);
        dialog.show();
    }
}
