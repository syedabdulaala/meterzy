package com.abdulaala.meterzy;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.abdulaala.meterzy.data.AppDatabase;

public class App extends Application {
    public AppDatabase getDatabase() {
        return database;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "meterzy").build();
    }

    private AppDatabase database;
}
