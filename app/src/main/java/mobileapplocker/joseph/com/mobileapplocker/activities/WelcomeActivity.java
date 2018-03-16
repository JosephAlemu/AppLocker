package mobileapplocker.joseph.com.mobileapplocker.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mobileapplocker.joseph.com.mobileapplocker.R;
import mobileapplocker.joseph.com.mobileapplocker.utils.SharedPreferencesManager;

public class WelcomeActivity extends AppCompatActivity {
    SharedPreferencesManager sharedPreferencesManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        String password =    sharedPreferencesManager.getPassowrd(this);

        if(!password.equals("0"))
        {
            Intent intent = new Intent(getApplicationContext(),InputPasswordActivity.class);
            startActivity(intent);
            finish();

        }
    }

    public void onAgreedClick(View view) {
        Intent intent = new Intent(getApplicationContext(),CreatePasswordActivity.class);
        startActivity(intent);
        finish();
    }
}
