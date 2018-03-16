package mobileapplocker.joseph.com.mobileapplocker.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import mobileapplocker.joseph.com.mobileapplocker.R;
import mobileapplocker.joseph.com.mobileapplocker.utils.SharedPreferencesManager;

public class CreatePasswordActivity extends AppCompatActivity  implements  NavigationView.OnNavigationItemSelectedListener{
    PatternLockView mPatternLockView;
    SharedPreferencesManager sharedPreferencesManager;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        // drawerLayout.setDrawerListener(toggle);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Password");

        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {

                sharedPreferencesManager.SetPassowrd(CreatePasswordActivity.this, PatternLockUtils.patternToString(mPatternLockView, pattern));
                Intent intent = new Intent(getApplicationContext(),InputPasswordActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCleared() {

            }
        });
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
}
