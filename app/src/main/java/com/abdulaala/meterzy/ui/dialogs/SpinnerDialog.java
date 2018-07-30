package com.abdulaala.meterzy.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.ui.adapters.SpinnerRVAdapter;
import com.abdulaala.meterzy.ui.callbacks.OnRvItemClickListener;
import com.abdulaala.meterzy.ui.models.SpinnerModel;

import java.util.List;

public class SpinnerDialog extends Dialog {
    //Variable(s)
    private RecyclerView rvSpinner;
    private List<SpinnerModel> dataSet;
    private OnRvItemClickListener listener;
    private SpinnerRVAdapter spinnerRVAdapter;

    //Constructor(s)
    public SpinnerDialog(Context context, List<SpinnerModel> dataSet) {
        super(context);
        this.dataSet = dataSet;
    }

    //Function(s)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_spinner);
        initializeComponent();
        super.onCreate(savedInstanceState);
    }

    private void initializeComponent() {
        int width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 0.80);
        int height = (int) (getContext().getResources().getDisplayMetrics().heightPixels * 0.70);
        getWindow().setLayout(width, height);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        rvSpinner = findViewById(R.id.rv_spinner);
        rvSpinner.setLayoutManager(new LinearLayoutManager(getContext()));
        spinnerRVAdapter = new SpinnerRVAdapter(getContext(), dataSet, listener);
        rvSpinner.setAdapter(spinnerRVAdapter);
    }

    public void setOnItemClickListener(OnRvItemClickListener listener) {
        this.listener = listener;
    }

    public SpinnerModel getSelected() {
        for (SpinnerModel spinnerModel : dataSet) {
            if (spinnerModel.isSelected())
                return spinnerModel;
        }
        return null;
    }
}
