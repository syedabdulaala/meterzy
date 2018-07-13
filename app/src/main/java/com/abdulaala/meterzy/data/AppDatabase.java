package com.abdulaala.meterzy.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.abdulaala.meterzy.data.Repo.FixedTariffRepo;
import com.abdulaala.meterzy.data.Repo.MeterReadingRepo;
import com.abdulaala.meterzy.data.Repo.MeterRepo;
import com.abdulaala.meterzy.data.Repo.RangeTariffRepo;
import com.abdulaala.meterzy.data.Repo.TariffRepo;
import com.abdulaala.meterzy.data.domain.FixedTariff;
import com.abdulaala.meterzy.data.domain.Meter;
import com.abdulaala.meterzy.data.domain.MeterReading;
import com.abdulaala.meterzy.data.domain.RangeTariff;
import com.abdulaala.meterzy.data.domain.Tariff;
import com.abdulaala.meterzy.util.Converter;

@Database(entities = {Meter.class, MeterReading.class, Tariff.class, FixedTariff.class, RangeTariff.class},
        version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract MeterRepo meterRepo();
    public abstract MeterReadingRepo meterReadingRepo();
    public abstract TariffRepo tariffRepo();
    public abstract FixedTariffRepo fixedTariffRepo();
    public abstract RangeTariffRepo rangeTariffRepo();
}
