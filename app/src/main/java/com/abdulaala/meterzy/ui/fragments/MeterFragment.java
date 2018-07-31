package com.abdulaala.meterzy.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.data.DataService;
import com.abdulaala.meterzy.data.domain.Tariff;
import com.abdulaala.meterzy.ui.adapters.SpinnerRVAdapter;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.callbacks.OnRvItemClickListener;
import com.abdulaala.meterzy.ui.dialogs.SpinnerDialog;
import com.abdulaala.meterzy.ui.models.SpinnerModel;

import java.util.ArrayList;
import java.util.List;

public class MeterFragment extends Fragment {
    //Variable(s)
    private MainContentCallback mainContentCallback;
    private List<SpinnerModel> tariffs;

    //Ui Componenet(s)
    private EditText etSpnrTariff;
    private Button btnCancel;

    //Constructor(s)
    public MeterFragment() {
    }

    //Function(s)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCancel = view.findViewById(R.id.btn_cancel);
        etSpnrTariff = view.findViewById(R.id.etSpnr_tariff);

        initListeners();
    }

    public void setMainContentCallback(MainContentCallback mainContentCallback) {
        this.mainContentCallback = mainContentCallback;
    }

    private void initListeners() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeterListFragment fragment = new MeterListFragment();
                fragment.setMainContentCallback(mainContentCallback);
                mainContentCallback.replaceMainContent(fragment);
            }
        });

        etSpnrTariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTariffFromDb();
                final SpinnerDialog spinnerDialog = new SpinnerDialog(getContext(), tariffs);
                spinnerDialog.setOnItemClickListener(new OnRvItemClickListener() {
                    @Override
                    public void OnClick(int position, Object item) {
                        etSpnrTariff.setText(((SpinnerModel) item).getName());
                        spinnerDialog.dismiss();
                    }
                });
                spinnerDialog.show();
            }
        });
    }

    private void getTariffFromDb() {
        List<Tariff> tariffs = DataService.getAppDb()
                .tariffRepo()
                .getAll();

        this.tariffs = new ArrayList<>();
        for (Tariff tariff: tariffs) {
            this.tariffs.add(new SpinnerModel(tariff.getId(), tariff.getName(), false));
        }
    }
}
