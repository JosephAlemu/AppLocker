package mobileapplocker.joseph.com.mobileapplocker.models;

import android.graphics.drawable.Drawable;

public class AppDetails {

	String appName;
	String packname;
	String appPackName;
	boolean appLockStates;
	Drawable icon;

	public AppDetails(String appName, String packname, String appPackName, boolean appLockStates, Drawable icon) {
		this.appName = appName;
		this.packname = packname;
		this.appPackName = appPackName;
		this.appLockStates = appLockStates;
		this.icon = icon;
	}

	public String getPackname() {
		return packname;
	}

	public void setPackname(String packname) {
		this.packname = packname;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppPackName() {
		return appPackName;
	}

	public void setAppPackName(String appPackName) {
		this.appPackName = appPackName;
	}

	public boolean isAppLockStates() {
		return appLockStates;
	}

	public void setAppLockStates(boolean appLockStates) {
		this.appLockStates = appLockStates;
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "AppDetails{" +
				"appName='" + appName + '\'' +
				", appPackName='" + appPackName + '\'' +
				", appLockStates=" + appLockStates +
				", icon=" + icon +
				'}';
	}

	public AppDetails() {

	}

}
