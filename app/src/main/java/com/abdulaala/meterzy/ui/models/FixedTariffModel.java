package com.abdulaala.meterzy.ui.models;

import com.abdulaala.meterzy.util.Constant;

public class FixedTariffModel {
    public FixedTariffModel(){}

    public FixedTariffModel(int id, String name, float charges, Constant.TariffType type) {
        this.id = id;
        this.name = name;
        this.charges = charges;
        this.type = type;
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

    public Float getCharges() {
        return charges;
    }

    public void setCharges(Float charges) {
        this.charges = charges;
    }

    public Constant.TariffType getType() {
        return type;
    }

    public void setType(Constant.TariffType type) {
        this.type = type;
    }

    private int id;
    private String name;
    private Float charges;
    private Constant.TariffType type;
}
