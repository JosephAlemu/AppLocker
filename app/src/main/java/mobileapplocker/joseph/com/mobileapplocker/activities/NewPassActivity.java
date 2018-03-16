package mobileapplocker.joseph.com.mobileapplocker.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

import mobileapplocker.joseph.com.mobileapplocker.R;
import mobileapplocker.joseph.com.mobileapplocker.utils.SharedPreferencesManager;

public class NewPassActivity extends AppCompatActivity {

    PatternLockView mPatternLockView;
    SharedPreferencesManager sharedPreferencesManager;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pass);getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Enter New Password");
        password = sharedPreferencesManager.getPassowrd(this);

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {


                sharedPreferencesManager.SetPassowrd(NewPassActivity.this, PatternLockUtils.patternToString(mPatternLockView, pattern));
                Intent intent = new Intent(getApplicationContext(),InputPasswordActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onCleared() {

            }
        });
    }
}
