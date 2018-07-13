package com.abdulaala.meterzy.data.Repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abdulaala.meterzy.data.domain.Meter;
import com.abdulaala.meterzy.data.domain.MeterReading;

import java.util.List;

@Dao
public interface MeterRepo {
    @Query("select * from Meter where id = :id limit 1;")
    Meter get(int id);

    @Query("select * from Meter;")
    List<Meter> getAll();

    @Query("select * from Meter where tariffId = :tariffId;")
    List<Meter> getAll(int tariffId);

    @Insert
    long insert(Meter meter);

    @Update
    void update(Meter meter);

    @Query("delete from Meter where id = :id;")
    void delete(int id);
}
