package com.abdulaala.meterzy.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.abdulaala.meterzy.R;
import com.abdulaala.meterzy.data.DataService;
import com.abdulaala.meterzy.data.domain.Meter;
import com.abdulaala.meterzy.data.domain.Tariff;
import com.abdulaala.meterzy.ui.callbacks.MainContentCallback;
import com.abdulaala.meterzy.ui.callbacks.OnRvItemClickListener;
import com.abdulaala.meterzy.ui.dialogs.SpinnerDialog;
import com.abdulaala.meterzy.ui.models.MeterModel;
import com.abdulaala.meterzy.ui.models.SpinnerModel;
import com.abdulaala.meterzy.util.BundleKey;

import java.util.ArrayList;
import java.util.List;

public class MeterFragment extends Fragment {
    //Variable(s)
    private MainContentCallback mainContentCallback;
    private List<SpinnerModel> tariffs;
    private MeterModel meter = new MeterModel();

    //Ui Component(s)
    private EditText etName;
    private EditText etConsumerNo;
    private EditText etAccountNo;
    private EditText etSpnrTariff;
    private Button btnCancel;
    private Button btnSave;

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

        etName = view.findViewById(R.id.et_name);
        etConsumerNo = view.findViewById(R.id.et_consumer_no);
        etAccountNo = view.findViewById(R.id.et_account_no);
        etSpnrTariff = view.findViewById(R.id.etSpnr_tariff);
        btnSave = view.findViewById(R.id.btn_save);
        btnCancel = view.findViewById(R.id.btn_cancel);

        populateData();
        initListeners();
    }

    public void setMainContentCallback(MainContentCallback mainContentCallback) {
        this.mainContentCallback = mainContentCallback;
    }

    private void initListeners() {
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    meter.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etAccountNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    meter.setAccountNo(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etConsumerNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0)
                    meter.setConsumerNo(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etSpnrTariff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSpinner();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDb();
                loadMeterListFragment();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMeterListFragment();
            }
        });


    }

    private void initSpinner() {
        getTariffFromDb();
        final SpinnerDialog spinnerDialog = new SpinnerDialog(getContext(), tariffs);
        spinnerDialog.setOnItemClickListener(new OnRvItemClickListener() {
            @Override
            public void OnClick(int position, Object item) {
                SpinnerModel spinnerModel = ((SpinnerModel) item);
                etSpnrTariff.setText(spinnerModel.getName());
                meter.setTariffId(spinnerModel.getId());
                spinnerDialog.dismiss();
            }
        });
        spinnerDialog.show();
    }

    private void getTariffFromDb() {
        if (this.tariffs != null && this.tariffs.size() != 0)
            return;
        List<Tariff> tariffs = DataService.getAppDb()
                .tariffRepo()
                .getAll();

        this.tariffs = new ArrayList<>();
        for (Tariff tariff : tariffs) {
            this.tariffs.add(new SpinnerModel(tariff.getId(), tariff.getName(), false));
        }
    }

    private void saveToDb() {
        if (meter.getId() == 0) {
            DataService.getAppDb()
                    .meterRepo()
                    .insert(new Meter(0, meter.getTariffId(), meter.getName(), meter.getConsumerNo(),
                            meter.getAccountNo()));
        } else {
            DataService.getAppDb()
                    .meterRepo()
                    .update(new Meter(meter.getId(), meter.getTariffId(), meter.getName(), meter.getConsumerNo(),
                            meter.getAccountNo()));
        }

        Toast.makeText(getContext(), "Saved!", Toast.LENGTH_SHORT).show();
    }

    private void loadMeterListFragment() {
        MeterListFragment fragment = new MeterListFragment();
        fragment.setMainContentCallback(mainContentCallback);
        mainContentCallback.replaceMainContent(fragment);
    }

    private MeterModel getMeterFromDb(int id) {
        Meter meter = DataService.getAppDb()
                .meterRepo()
                .get(id);
        return new MeterModel(meter.getId(), meter.getTariffId(), meter.getName(), meter.getAccountNo(),
                meter.getConsumerNo());
    }

    private void populateData() {
        if (getArguments() != null) {
            meter.setId(getArguments().getInt(BundleKey.METER_ID, 0));
            if (meter.getId() != 0) {
                meter = getMeterFromDb(meter.getId());
                getTariffFromDb();
                etName.setText(meter.getName());
                etAccountNo.setText(meter.getAccountNo());
                etConsumerNo.setText(meter.getConsumerNo());
                for (int i = 0; i < tariffs.size(); i++) {
                    if (tariffs.get(i).getId() == meter.getTariffId()) {
                        tariffs.get(i).setSelected(true);
                        etSpnrTariff.setText(tariffs.get(i).getName());
                    }
                }
            }
        }
    }
}
