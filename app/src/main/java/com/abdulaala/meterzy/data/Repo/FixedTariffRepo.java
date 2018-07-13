package com.abdulaala.meterzy.data.Repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;
import android.arch.persistence.room.Update;

import com.abdulaala.meterzy.data.domain.FixedTariff;

import java.util.List;

@Dao
public interface FixedTariffRepo {
    @Query("select * from FixedTariff where id = :id limit 1;")
    FixedTariff get(int id);

    @Query("select * from FixedTariff where tariffId = :tariffId;")
    List<FixedTariff> getAll(int tariffId);

    @Insert
    long insert(FixedTariff fixedTariff);

    @Update
    void update(FixedTariff fixedTariff);

    @Query("delete from FixedTariff where id = :id;")
    void delete(int id);
}
