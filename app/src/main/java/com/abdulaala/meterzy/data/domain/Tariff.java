package com.abdulaala.meterzy.data.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public final class Tariff {
    public Tariff(int id, String name, String currency) {
        this.id = id;
        this.name = name;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String currency;
}
