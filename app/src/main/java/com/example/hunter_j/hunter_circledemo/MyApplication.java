package com.example.hunter_j.hunter_circledemo;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by hunter_J on 2017/11/2.
 */

public class MyApplication extends Application {

    //默认图片存放的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() +
            File.separator + "H_CircleDemo" + File.separator + "Images" + File.separator;

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

//        MultiDex.install(this);
    }

    public static Context getContext(){
        return mContext;
    }
}
