package com.abdulaala.meterzy.ui.models;

import com.abdulaala.meterzy.util.Constant;

public class RangeTariffModel {
    public RangeTariffModel() {

    }

    public RangeTariffModel(int id, String name, Float startRange, Float endRange, Float charges, Constant.TariffType type) {
        this.id = id;
        this.name = name;
        this.startRange = startRange;
        this.endRange = endRange;
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

    public Float getStartRange() {
        return startRange;
    }

    public void setStartRange(Float startRange) {
        this.startRange = startRange;
    }

    public Float getEndRange() {
        return endRange;
    }

    public void setEndRange(Float endRange) {
        this.endRange = endRange;
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
    private Float startRange;
    private Float endRange;
    private Float charges;
    private Constant.TariffType type;
}
