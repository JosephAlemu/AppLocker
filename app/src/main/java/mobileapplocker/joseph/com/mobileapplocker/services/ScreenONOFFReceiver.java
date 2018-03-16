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

public class ScreenONOFFReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d("ScreenONOFFReceiver", "Screen Off");

            Intent i = new Intent(context, ForegroundAppSnifferService.class);
            context.stopService(i);
            Toast.makeText(context, "SCREEN_OFF stopService", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            Log.d("ScreenONOFFReceiver", "Screen On");
            Toast.makeText(context, "SCREEN_ON", Toast.LENGTH_SHORT).show();
        }

        else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT))  {
            Log.d("ScreenONOFFReceiver", "ACTION_USER_PRESENT");
            Toast.makeText(context, "ACTION_USER_PRESENT", Toast.LENGTH_SHORT).show();
        }
    }
}