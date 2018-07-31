package com.abdulaala.meterzy.data.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Tariff.class, parentColumns = "id",
        childColumns = "tariffId", onDelete = ForeignKey.SET_NULL),
        indices = {@Index(value = {"tariffId"})})
public final class Meter {
    public Meter(int id, Integer tariffId, String name, String consumerNo, String accountNo) {
        this.id = id;
        this.tariffId = tariffId;
        this.name = name;
        this.consumerNo = consumerNo;
        this.accountNo = accountNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTariffId() {
        return tariffId;
    }

    public void setTariffId(Integer tariffId) {
        this.tariffId = tariffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(String consumerNo) {
        this.consumerNo = consumerNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Integer tariffId;
    private String name;
    private String consumerNo;
    private String accountNo;
}
