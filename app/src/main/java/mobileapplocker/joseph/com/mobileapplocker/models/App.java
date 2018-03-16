package mobileapplocker.joseph.com.mobileapplocker.models;

/**
 * Created by Admin on 3/7/2018.
 */

public class App {

    String appName;
    String appPackageName;

    public App(String appName, String appPackageName) {
        this.appName = appName;
        this.appPackageName = appPackageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }
}
