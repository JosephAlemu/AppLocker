package mobileapplocker.joseph.com.mobileapplocker.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mobileapplocker.joseph.com.mobileapplocker.models.App;
import mobileapplocker.joseph.com.mobileapplocker.utils.Constant;


/**
 * Created by Admin on 3/5/2018.
 */
public class SharedPreferencesManager {


    private static final String APP_SETTINGS = "APP_SETTINGS";

    // properties
    private static final String SOME_STRING_VALUE = "SOME_STRING_VALUE";
    // other properties...


    private SharedPreferencesManager() {
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getSomeStringValue(Context context) {
        return getSharedPreferences(context).getString(SOME_STRING_VALUE, null);
    }


    public static boolean addApp(String AppName, Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        List<String> lockAppNameList =  getLockAppName(context);
     //   lockAppNameList.add(AppName);

        Set<String> set = new HashSet<String>();
        set.addAll(lockAppNameList);
        set.add(AppName);
        editor.putStringSet("lockAppName", set);
        editor.apply();

        return true;
    }
    public static boolean removeApp(String AppName, Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        List<String> lockAppNameList =  getLockAppName(context);
       // lockAppNameList.remove(AppName);

        Set<String> set = new HashSet<String>();
        set.addAll(lockAppNameList);
        set.remove(AppName);
        editor.putStringSet("lockAppName", set);
        editor.apply();

        return true;
    }

    public static String getPassowrd(Context context) {

        SharedPreferences sharedPreferences = getSharedPreferences(context);
        String password = sharedPreferences.getString("password", "0");
        return password;
    }

    public static void SetPassowrd(Context context, String strPassword) {

        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", strPassword);
        editor.apply();

    }
    public static void cleanLockApplist(Context context) {

        SharedPreferences sharedPreferences = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> set = new HashSet<String>();
        editor.putStringSet("lockAppName", set);
        editor.apply();

        editor.apply();

    }

    public static List<String> getLockAppName(Context context) {

         SharedPreferences sharedPreferences = getSharedPreferences(context);
         Set<String> set = sharedPreferences.getStringSet("lockAppName", null);
          List<String> lockAppNameList;
         if((set != null) && !set.isEmpty()){
            lockAppNameList =new ArrayList<String>(set);
             return lockAppNameList;
         }else {
             return Collections.emptyList();
         }




    }

}