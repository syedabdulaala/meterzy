package com.abdulaala.meterzy.data.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.abdulaala.meterzy.util.Constant;

@Entity(foreignKeys = @ForeignKey(entity = Tariff.class, parentColumns = "id",
        childColumns = "tariffId", onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = {"tariffId"})})
public final class RangeTariff {
    public RangeTariff(int id, int tariffId, float startRange, float endRange, float charges, String name, Constant.TariffType type) {
        this.id = id;
        this.tariffId = tariffId;
        this.startRange = startRange;
        this.endRange = endRange;
        this.charges = charges;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public float getStartRange() {
        return startRange;
    }

    public void setStartRange(float startRange) {
        this.startRange = startRange;
    }

    public float getEndRange() {
        return endRange;
    }

    public void setEndRange(float endRange) {
        this.endRange = endRange;
    }

    public float getCharges() {
        return charges;
    }

    public void setCharges(float charges) {
        this.charges = charges;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Constant.TariffType getType() {
        return type;
    }

    public void setType(Constant.TariffType type) {
        this.type = type;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int tariffId;
    private float startRange;
    private float endRange;
    private float charges;
    private String name;
    private Constant.TariffType type;
}
