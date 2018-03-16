package mobileapplocker.joseph.com.mobileapplocker.services.model;

import android.app.Service;
import android.app.usage.UsageStats;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mobileapplocker.joseph.com.mobileapplocker.activities.InputPasswordActivity;
import mobileapplocker.joseph.com.mobileapplocker.services.ScreenONOFFReceiver;
import mobileapplocker.joseph.com.mobileapplocker.services.model.versioncontrollayer.AndroidVerionFactory;
import mobileapplocker.joseph.com.mobileapplocker.services.model.versioncontrollayer.AndroidVersionInterface;
import mobileapplocker.joseph.com.mobileapplocker.utils.SharedPreferencesManager;


/**
 * Created by srinivasan.l on 4/30/2017.
 */

public class ForegroundAppSnifferService extends Service {
    public static  long NOTIFY_INTERVAL=5000; // 5 seconds can be configurable
    // run on another Thread to avoid crash
    private final Handler mHandler = new Handler();
    private static final String TAG = ForegroundAppSnifferService.class.getSimpleName()+"--->";
    private Timer mTimer;
    Context ctx;
    private String serviceName=null;
    private static String foregroundApp="";


    List<String> lockAppList;
    BroadcastReceiver mScreenReceiver;
    SharedPreferencesManager sharedPreferencesManager;


    @Override
    public void onCreate() {

        ctx=this.getApplicationContext();

        registerScreenStatusReceiver();

        serviceName = "ForegroundAppSnifferService";

        if (this.mTimer != null) {
            this.mTimer.cancel();
        } else {
            // recreate new
            this.mTimer = new Timer();
        }

        // schedule task
        Log.d("SnifferService--->","Inside");
        this.mTimer.scheduleAtFixedRate(new ForegroundAppSnifferService.AppSnifferTask(), 0,
                ForegroundAppSnifferService.NOTIFY_INTERVAL);


        super.onCreate();

    }

    class AppSnifferTask extends TimerTask {
        public String currentApp = "NULL";

        @Override
        public void run() {
            // run on another thread
            //int threadCount=Thread.activeCount();
            boolean post = ForegroundAppSnifferService.this.mHandler.post(new Runnable() {


                @Override
                public void run() {
                    List<UsageStats> appList;// initialization of list to store usagestats
                    AndroidVersionInterface androidVersionInterface =
                            AndroidVerionFactory.getPackageAccessAbstractionObject();
                    if (androidVersionInterface != null) {
                        currentApp = androidVersionInterface.getForegroundRunningApp(ctx);





                        Toast.makeText(getApplicationContext(), "CurrentApp-->" + currentApp,
                                Toast.LENGTH_SHORT)
                                .show();
                    }
                    //passing the currentApp to a static variable


                    if (currentApp != null && !foregroundApp.equalsIgnoreCase(currentApp)) {
                       /*checks there is a change in the foregound app,
                       if conditon passes ,falls into this set statements
                       **/
                        Toast.makeText(getApplicationContext(), "AppSniffer packageStop-->" + foregroundApp,
                                Toast.LENGTH_SHORT)
                                .show();


                        lockAppList = sharedPreferencesManager.getLockAppName(ForegroundAppSnifferService.this);

                        if (lockAppList.contains(currentApp)) {


                            Intent intent = new Intent(ForegroundAppSnifferService.this, InputPasswordActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                            startActivity(intent);
                            try {
                                Thread.sleep(6000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }


                        Log.d(TAG, "previous App in foreground is: " + foregroundApp);

                        if (currentApp.equals("com.netflix.mediaclient") ||
                                currentApp.equals("com.whatsapp")) {


                            // to get internal sessionId
                            Toast.makeText(getApplicationContext(), "AppSniffer package-->" + currentApp,
                                    Toast.LENGTH_LONG)
                                    .show();
                        }


                        foregroundApp = currentApp;
                    }
                }
            });

        }
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       //to restart if crashes
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        unregisterScreenStatusReceiver();
    }

    private void registerScreenStatusReceiver() {
        mScreenReceiver = new ScreenONOFFReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mScreenReceiver, filter);
    }

    private void unregisterScreenStatusReceiver() {
        try {
            if (mScreenReceiver != null) {
                unregisterReceiver(mScreenReceiver);
            }
        } catch (IllegalArgumentException e) {}
    }

}