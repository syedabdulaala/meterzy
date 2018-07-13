package com.abdulaala.meterzy.data.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;

@Entity(foreignKeys = @ForeignKey(entity = Meter.class, parentColumns = "id",
        childColumns = "meterId", onDelete = ForeignKey.CASCADE),
        indices = {@Index(value = {"meterId"})})
public final class MeterReading {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeterId() {
        return meterId;
    }

    public void setMeterId(int meterId) {
        this.meterId = meterId;
    }

    public float getReading() {
        return reading;
    }

    public void setReading(float reading) {
        this.reading = reading;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int meterId;
    private float reading;
    private Date date;
}
