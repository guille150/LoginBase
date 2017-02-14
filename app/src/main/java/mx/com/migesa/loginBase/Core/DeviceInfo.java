/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package mx.com.migesa.loginBase.Core;

/**
 * Created by Guillermo Ortiz on 31/05/2016.
 */

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.util.Locale;
import java.util.TimeZone;

@SuppressWarnings("WrongConstant")
public class DeviceInfo {
    public DeviceInfo() {
    }

    public static String getOSVersion() {
        return VERSION.RELEASE;
    }

    public static String getDevice() {
        return Build.MODEL;
    }

    public static String getResolution(Context context) {
        WindowManager wm = (WindowManager)context.getSystemService("window");
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.heightPixels + "x" + metrics.widthPixels;
    }

    public static String getCarrier(Context context) {
        TelephonyManager manager = (TelephonyManager)context.getSystemService("phone");
        return manager.getNetworkOperatorName();
    }

    public static String getMCC(Context context) {
        TelephonyManager manager = (TelephonyManager)context.getSystemService("phone");
        return manager.getNetworkCountryIso();
    }

    public static String getLocale() {
        Locale locale = Locale.getDefault();
        return locale.getLanguage() + "_" + locale.getCountry();
    }

    public static String getTz() {
        TimeZone timezone = TimeZone.getDefault();
        return timezone.getID();
    }

    public static String getUDID(Context context) {
        String udid = Secure.getString(context.getContentResolver(), "android_id");
        if(udid == null) {
            udid = "";
        }

        return udid;
    }

    public static String appVersion(Context context) {
        String result = "1.0";

        try {
            result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException var3) {
            ;
        }

        return result;
    }
}
