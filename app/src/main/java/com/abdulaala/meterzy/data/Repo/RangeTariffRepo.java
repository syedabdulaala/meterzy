package com.abdulaala.meterzy.data.Repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abdulaala.meterzy.data.domain.Meter;
import com.abdulaala.meterzy.data.domain.RangeTariff;

import java.util.List;

@Dao
public interface RangeTariffRepo {
    @Query("select * from RangeTariff where id = :id limit 1;")
    RangeTariff get(int id);

    @Query("select * from RangeTariff where tariffId = :tariffId;")
    List<RangeTariff> getAll(int tariffId);

    @Insert
    long insert(RangeTariff rangeTariff);

    @Update
    void update(RangeTariff rangeTariff);

    @Query("delete from RangeTariff where id = :id;")
    void delete(int id);
}
