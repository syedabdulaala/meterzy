package com.abdulaala.meterzy.util;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

public class Converter {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Constant.TariffType fromInt(int value) { return Constant.TariffType.values()[value]; }

    @TypeConverter
    public static int tariffTypeToInt(Constant.TariffType type) { return type == null ? null : type.ordinal(); }
}
