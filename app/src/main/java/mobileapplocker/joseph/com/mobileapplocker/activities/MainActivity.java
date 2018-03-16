package mobileapplocker.joseph.com.mobileapplocker.activities;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import mobileapplocker.joseph.com.mobileapplocker.R;
import mobileapplocker.joseph.com.mobileapplocker.services.model.ForegroundAppSnifferService;
import mobileapplocker.joseph.com.mobileapplocker.services.model.versioncontrollayer.AndroidPermissionHandlerFactory;
import mobileapplocker.joseph.com.mobileapplocker.services.model.versioncontrollayer.AndroidPermissionHandlerInterface;
import mobileapplocker.joseph.com.mobileapplocker.utils.SharedPreferencesManager;
import mobileapplocker.joseph.com.mobileapplocker.models.AppDetails;
import mobileapplocker.joseph.com.mobileapplocker.utils.AppListAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener {
    private ListView mListView;
    private AppListAdapter mApListAdapter;
    private ProgressBar mProgressBar;
    private Toolbar toolbar;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ActionBarDrawerToggle toggle;
    List<AppDetails> installedAppList = null;
    List<String> lockAppList;
    SharedPreferencesManager sharedPreferencesManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initViews();


        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "float", Toast.LENGTH_SHORT).show();

                sharedPreferencesManager.cleanLockApplist(MainActivity.this);

                upDateListView();
            }
        });
    }

    private void initViews() {

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        // drawerLayout.setDrawerListener(toggle);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mListView = findViewById(R.id.list_view);

        // Add a header to the ListView
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_header, mListView, false);
        mListView.addHeaderView(header);
        mListView.setVisibility(View.GONE);
        new AppDataAysnc().execute();

        mListView.setOnItemClickListener(MainActivity.this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (toggle.onOptionsItemSelected(item)) {

            return true;
        } else {
            switch (id) {


                case R.id.resetPassword:
                    Toast.makeText(this, "reset Password", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this,ResetActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case R.id.setPassword:
                    Toast.makeText(this, "set Password", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Toast.makeText(getApplicationContext(), "default", Toast.LENGTH_LONG).show();
            }
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.d("MAinACtivity-->", "OnResume");
        checkUsageStatsPermission();

        super.onResume();
    }

    private void checkUsageStatsPermission() {
        AndroidPermissionHandlerInterface androidVersionObj = AndroidPermissionHandlerFactory.
                getAndroidPermissionversionObject();
        androidVersionObj.checkUsageStats(getApplicationContext());
        androidVersionObj.askUsageStats(getApplicationContext());

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        String packname;
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();

        int pos = position - 1;
        if (lockAppList.contains(installedAppList.get(pos).getPackname())) {

            packname = installedAppList.get(pos).getPackname();
            sharedPreferencesManager.removeApp(packname, MainActivity.this);
        } else {
            packname = installedAppList.get(pos).getPackname();
            //packname = installedAppList.get(pos).getAppName();
            sharedPreferencesManager.addApp(packname, MainActivity.this);
        }
        upDateListView();

    }


    //

    private void upDateListView() {
        lockAppList = sharedPreferencesManager.getLockAppName(MainActivity.this);
        mApListAdapter.passUpdatedList(lockAppList);
        mApListAdapter.notifyDataSetChanged();
        mListView.invalidateViews();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id) {

            case R.id.security_id:

                Toast.makeText(getApplicationContext(), "Security", Toast.LENGTH_LONG).show();

                break;


            case R.id.Safe_id:

                Toast.makeText(getApplicationContext(), "safe", Toast.LENGTH_LONG).show();

                break;



            case R.id.settings_id:

                Toast.makeText(getApplicationContext(), "setting", Toast.LENGTH_LONG).show();

                break;

            case R.id.activate_id:

                Toast.makeText(getApplicationContext(), "activate", Toast.LENGTH_LONG).show();

                break;


            case R.id.share_id:

                Toast.makeText(getApplicationContext(), "share", Toast.LENGTH_LONG).show();

                break;


            case R.id.rate_id:

                Toast.makeText(getApplicationContext(), "rate", Toast.LENGTH_LONG).show();

                break;


            case R.id.help_id:

                Toast.makeText(getApplicationContext(), "help", Toast.LENGTH_LONG).show();

                break;


            case R.id.about_id:

                Toast.makeText(getApplicationContext(), "about", Toast.LENGTH_LONG).show();

                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }

    public class AppDataAysnc extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {


            installedAppList = new ArrayList<>();
            lockAppList = sharedPreferencesManager.getLockAppName(MainActivity.this);

            PackageManager packageManager = getApplicationContext().getPackageManager();

            // get a list of installed apps.
            List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

            for (ApplicationInfo packageInfo : packages) {

                try {
                    if (null != packageManager.getLaunchIntentForPackage(packageInfo.packageName)) {

                        String packname = packageInfo.packageName;
                        String appName = packageInfo.loadLabel(getApplicationContext().getPackageManager()).toString();
                        //   Log.i(MainActivity.class.toString(), "MainActivity Application Name is :"  + appName);
                        String processName = packageInfo.processName;
                        // String clasName = packageInfo.getClass();
                        //   Log.i(MainActivity.class.toString(), "Process Name is :"+ processName);
                        Drawable app_icon = packageInfo.loadIcon(getApplicationContext().getPackageManager());

                        AppDetails appDetails = new AppDetails(appName, packname, processName, false, app_icon);

                        installedAppList.add(appDetails);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            Collections.sort(installedAppList, new appNameComparator());
            return null;
        }

        public class appNameComparator implements Comparator<AppDetails> {
            public int compare(AppDetails o1, AppDetails o2) {
                return o1.getAppName().compareTo(o2.getAppName());
            }
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mProgressBar.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            // TODO    mApListAdapter = new AppListAdapter(getApplicationContext(),   mArrayAppList, mArrAppDetails);
            // TODO   mListView.setAdapter(mApListAdapter);

            mApListAdapter = new AppListAdapter(MainActivity.this, lockAppList, installedAppList);
            mListView.setAdapter(mApListAdapter);

        }


        public Bitmap drawableToBitmap(Drawable drawable) {
            Bitmap bitmap = null;

            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if (bitmapDrawable.getBitmap() != null) {
                    return bitmapDrawable.getBitmap();
                }
            }

            if (drawable.getIntrinsicWidth() <= 0
                    || drawable.getIntrinsicHeight() <= 0) {
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }

        /**
         * @param bitmap is an Bitmap object
         * @see this method used to convert the bitmap into string conversion
         */
        public String BitMapToString(Bitmap bitmap) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] arr = baos.toByteArray();
            String result = Base64.encodeToString(arr, Base64.DEFAULT);
            return result;
        }


    }


}
