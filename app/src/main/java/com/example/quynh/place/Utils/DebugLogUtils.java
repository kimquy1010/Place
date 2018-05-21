package com.example.quynh.place.Utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.quynh.place.BuildConfig;


/**
 * Created by macos on 17/03/2017.
 */

public class DebugLogUtils {

    public static final boolean ALLOW_DEBUG = BuildConfig.DEBUG;
    public static final String DATABASE_TAG = "Database_Tag";
    public static final String EDITOR_TAG = "Editor_Tag";
    public static final String FILE_TAG = "File_Tag";
    public static final String CAMERA_LOG = "Camera_Tag";
    public static final String IAB_TAG = "Iab_Tag";
    public static final String FLOAT_VIEW_TAG = "FloatView_Tag";
    public static final String ADS_TAG = "Ads";
    public static final String APP_TAG = "AzScreenRecorder";

    public static void i(String tag, String msg) {
        if (ALLOW_DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (ALLOW_DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (ALLOW_DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (ALLOW_DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (ALLOW_DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable e) {
        if (ALLOW_DEBUG && !TextUtils.isEmpty(tag) && !TextUtils.isEmpty(msg)) {
            Log.e(tag, msg, e);
        }
    }

    public static void e(String tag, Throwable e) {
        if (ALLOW_DEBUG && !TextUtils.isEmpty(tag) ) {
            Log.e(tag, "zz", e);
        }
    }

    public static void appLog(String msg) {
        v(APP_TAG, msg);
    }

    public static void appLog(Throwable e) {
        e(APP_TAG, e.getClass().getSimpleName(), e);
    }

    public static void floatViewLog(String msg) {
        v(FLOAT_VIEW_TAG, msg);
    }

    public static void floatViewLog(Throwable e) {
        e(FLOAT_VIEW_TAG, e.getClass().getSimpleName(), e);
    }

    public static void appLog(String msg, Throwable e) {
        e(APP_TAG, msg, e);
    }

    public static void databaseLog(String msg) {
        v(DATABASE_TAG, msg);
    }

    public static void editorLog(String msg) {
        v(EDITOR_TAG, msg);
    }

    public static void editorLog(Throwable e) {
        e(EDITOR_TAG, e);
    }

    public static void fileLog(String msg) {
        v(FILE_TAG, msg);
    }

    public static void fileLog(String msg, Throwable e) {
        e(FILE_TAG, msg, e);
    }

    public static void cameraLog(String msg, Throwable e) {
        e(CAMERA_LOG, msg, e);
    }

    public static void cameraLog(String msg) {
        v(CAMERA_LOG, msg);
    }

    public static void iabLog(String msg, Throwable e) {
        e(IAB_TAG, msg, e);
    }

    public static void iabLog(String msg) {
        v(IAB_TAG, msg);
    }

    public static void adsLog(String msg) {
        v(ADS_TAG, msg);
    }

    public static void adsLog(Throwable e) {
        e(ADS_TAG, e);
    }
}