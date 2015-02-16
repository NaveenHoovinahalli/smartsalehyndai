package com.hyundai.teli.smartsales.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Point;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.view.Display;
import android.view.WindowManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public abstract class AndroidUtils {

    private AndroidUtils() {
    }

    public static String getAppName(Context context) {
        return getAppName(context, null);
    }

    public static String getAppName(Context context, String packageName) {
        String applicationName;

        if (packageName == null) {
            packageName = context.getPackageName();
        }

        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            applicationName = context.getString(packageInfo.applicationInfo.labelRes);
        } catch (Exception e) {
            applicationName = "";
        }

        return applicationName;
    }


    public static String getAppVersionNumber(Context context) {
        return getAppVersionNumber(context, null);
    }

    public static String getAppVersionNumber(Context context, String packageName) {
        String versionName;

        if (packageName == null) {
            packageName = context.getPackageName();
        }

        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            versionName = packageInfo.versionName;
        } catch (Exception e) {
            versionName = "";
        }

        return versionName;
    }

    public static String getAppVersionCode(Context context) {
        return getAppVersionCode(context, null);
    }

    public static String getAppVersionCode(Context context, String packageName) {
        String versionCode;

        if (packageName == null) {
            packageName = context.getPackageName();
        }

        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            versionCode = Integer.toString(packageInfo.versionCode);
        } catch (Exception e) {
            versionCode = "";
        }

        return versionCode;
    }

    public static String getDeviceModel() {
        return Build.MODEL;
    }

    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getOsVersionName() {
        return Build.VERSION.RELEASE;
    }

    public static int getSdkVersion() {
        try {
            return Build.VERSION.class.getField("SDK_INT").getInt(null);
        } catch (Exception e) {
            return 3;
        }
    }

    public static String getUniqueId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getDeviceImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    public static boolean isEmulator() {
        return Build.MODEL.equals("sdk") || Build.MODEL.equals("google_sdk");
    }


    @TargetApi(Build.VERSION_CODES.FROYO)
    public static String getKeyHash(Context context) {

        String hash = null;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                hash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {
        }
        return hash;
    }

    public static int[] getDeviceDimensions(Context context) {
        int[] dimens = new int[2];
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(size);
            dimens[0] = size.x;
            dimens[1] = size.y;
        } else {
            dimens[0] = display.getWidth();  // deprecated
            dimens[1] = display.getHeight();
        }
        return dimens;
    }

    public static Location getUserLocation(Context context) {

        LocationManager lManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        boolean enabledGps = lManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean enabledNetwork = lManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Criteria criteria = new Criteria();
        String provider = lManager.getBestProvider(criteria, false);
        Location location = lManager.getLastKnownLocation(provider);

        if (enabledGps || enabledNetwork) {
            if (location != null) {
                return location;
            } else {
                //Toast.makeText(context, "Unable to fetch location", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    public static Location getLastKnownLocation(Context context) {

        LocationManager lManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        List<String> providers = lManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location lastKnownLocation = lManager.getLastKnownLocation(provider);

            if (lastKnownLocation == null) {
                continue;
            }
            if (bestLocation == null
                    || lastKnownLocation.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = lastKnownLocation;
            }
        }
        return bestLocation;
    }

    public static String getDeviceLanguage() {
        return Locale.getDefault().getLanguage();
    }


    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNetworkOnline(Context context) {
        boolean status = false;
        try {
            boolean haveConnectedWifi = false;
            boolean haveConnectedMobile = false;

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected())
                        haveConnectedWifi = true;
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected())
                        haveConnectedMobile = true;
            }
            return haveConnectedWifi || haveConnectedMobile;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
