package com.bw.movie.utils;

import android.app.Application;
import android.content.SharedPreferences;


import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {

    public static SharedPreferences mLogin;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mLogin = getSharedPreferences("Login", MODE_PRIVATE);
    }
}
