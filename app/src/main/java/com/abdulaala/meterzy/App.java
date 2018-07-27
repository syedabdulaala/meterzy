package com.abdulaala.meterzy;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.abdulaala.meterzy.data.AppDatabase;
import com.abdulaala.meterzy.data.DataService;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataService.initAppDb(getApplicationContext());
    }
}
