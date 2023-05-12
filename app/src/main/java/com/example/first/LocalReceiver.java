package com.example.first;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;

import androidx.annotation.RequiresApi;

public class LocalReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager.WakeLock wakeLock = ((PowerManager) context.getSystemService(Context.POWER_SERVICE)).newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, ":YourService");
        wakeLock.acquire(30 * 1L); //It will keep the device awake & register the service within 1 minute time duration.
        context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, MainActivity.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            startMainService(context);
        }
        wakeLock.release(); //Don't forget to add this line when using the wakelock
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startMainService(Context context) {
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, LocalReceiver.class), PendingIntent.FLAG_IMMUTABLE);
    }


}
