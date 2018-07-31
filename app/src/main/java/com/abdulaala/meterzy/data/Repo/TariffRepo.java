package com.abdulaala.meterzy.data.Repo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.abdulaala.meterzy.data.domain.RangeTariff;
import com.abdulaala.meterzy.data.domain.Tariff;

import java.util.List;

@Dao
public interface TariffRepo {
    @Query("select * from Tariff where id = :id limit 1;")
    Tariff get(int id);

    @Query("select * from tariff;")
    List<Tariff> getAll();

    @Insert
    long insert(Tariff tariff);

    @Update
    void update(Tariff tariff);

    @Query("delete from Tariff where id = :id;")
    void delete(int id);
}
