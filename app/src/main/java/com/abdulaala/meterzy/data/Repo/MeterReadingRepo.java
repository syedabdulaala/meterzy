package com.abdulaala.meterzy.data.Repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abdulaala.meterzy.data.domain.FixedTariff;
import com.abdulaala.meterzy.data.domain.MeterReading;

import java.sql.Date;
import java.util.List;

@Dao
public interface MeterReadingRepo {
    @Query("select * from MeterReading where id = :id limit 1;")
    MeterReading get(int id);

    @Query("select * from MeterReading where date = :date limit 1;")
    MeterReading get(Date date);

    @Query("select * from MeterReading;")
    List<MeterReading> getAll();

    @Query("select * from MeterReading where meterId = :meterId and date between :startDate and :endDate;")
    List<MeterReading> getAll(int meterId, Date startDate, Date endDate);

    @Query("select * from MeterReading where meterId = :meterId;")
    List<MeterReading> getAll(int meterId);

    @Insert
    long insert(MeterReading meterReading);

    @Update
    void update(MeterReading meterReading);

    @Query("delete from MeterReading where id = :id;")
    void delete(int id);

    @Query("delete from MeterReading where meterId = :meterId;")
    void deleteAll(int meterId);
}
