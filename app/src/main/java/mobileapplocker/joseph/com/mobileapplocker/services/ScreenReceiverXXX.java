package mobileapplocker.joseph.com.mobileapplocker.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import mobileapplocker.joseph.com.mobileapplocker.services.model.ForegroundAppSnifferService;

/**
 * Created by user on 3/11/2018.
 */

public class ScreenReceiverXXX extends BroadcastReceiver {

    private boolean screenOn;
    private static final String TAG = "Screen Receiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            screenOn = true;


            Intent intent1 = new Intent(context, ForegroundAppSnifferService.class);
            context.startService(intent1);


            Toast.makeText(context, "screen on! startService", Toast.LENGTH_SHORT).show();

            // context.startService(i);
            Log.d(TAG, "screen on! startService");
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Toast.makeText(context, " MEEEE _SCREEN_OFF", Toast.LENGTH_SHORT).show();
            screenOn = false;
        }
    }
}