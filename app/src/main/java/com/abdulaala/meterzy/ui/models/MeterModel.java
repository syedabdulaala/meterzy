package com.abdulaala.meterzy.ui.models;

import java.util.Date;

public class MeterModel {
    public MeterModel() {

    }

    public MeterModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MeterModel(int id, int tariffId, String name, String accountNo, String consumerNo) {
        this.id = id;
        this.tariffId = tariffId;
        this.name = name;
        this.accountNo = accountNo;
        this.consumerNo = consumerNo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getConsumerNo() {
        return consumerNo;
    }

    public void setConsumerNo(String consumerNo) {
        this.consumerNo = consumerNo;
    }

    private int id;
    private int tariffId;
    private String name;
    private String accountNo;
    private String consumerNo;
}
