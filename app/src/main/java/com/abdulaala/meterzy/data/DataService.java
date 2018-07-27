package com.abdulaala.meterzy.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.abdulaala.meterzy.App;

public class DataService {
    private static final DataService ourInstance = new DataService();
    private static AppDatabase appDatabase;

    public static DataService getInstance() {
        return ourInstance;
    }

    private DataService() {
    }

    public static void initAppDb(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "meterzy")
                .allowMainThreadQueries()
                .build();
    }

    public static AppDatabase getAppDb() {
        return appDatabase;
    }
}
