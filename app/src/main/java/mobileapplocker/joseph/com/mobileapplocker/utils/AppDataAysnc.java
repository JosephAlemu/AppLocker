package mobileapplocker.joseph.com.mobileapplocker.utils;

/**
 * Created by Admin on 3/7/2018.
 */
//public class AppDataAysnc extends AsyncTask<Context, Void, String> {
//
//    SharedPreferencesManager sharedPreferencesManager;
//    List<AppDetails> installedAppList = null;
//    List<String> lockAppList;
//    AppInfoInterface appInfoInterface;
//   Context context;
//    @Override
//    protected void onPreExecute() {
//
//        super.onPreExecute();
//
//    }
//
//    @Override
//    protected String doInBackground(Context... params) {
//        final Context context = params[0];
//
//        installedAppList = new ArrayList<>();
//        lockAppList =   sharedPreferencesManager.json_Object(context);
//
//        PackageManager packageManager = context.getPackageManager();
//
//        // get a list of installed apps.
//        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
//
//        for (ApplicationInfo packageInfo : packages) {
//            String appName = packageInfo.loadLabel(context.getPackageManager()).toString();
//         //   Log.i(MainActivity.class.toString(), "MainActivity Application Name is :"  + appName);
//            String processName = packageInfo.processName;
//         //   Log.i(MainActivity.class.toString(), "Process Name is :"+ processName);
//            Drawable app_icon = packageInfo.loadIcon(context.getPackageManager());
//
//
//            AppDetails appDetails = new AppDetails(appName, processName,BitMapToString(drawableToBitmap(app_icon)), "0");
//
//            installedAppList.add(appDetails);
//
//
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        super.onPostExecute(result);
//      // TODO  mProgressBar.setVisibility(View.GONE);
//        // TODO   mListView.setVisibility(View.VISIBLE);
//        // TODO    mApListAdapter = new AppListAdapter(getApplicationContext(),   mArrayAppList, mArrAppDetails);
//        // TODO   mListView.setAdapter(mApListAdapter);
//        appInfoInterface.onBackgroundTaskDataObtained(lockAppList,installedAppList);
//
//    }
//
//
//
//    public static Bitmap drawableToBitmap(Drawable drawable) {
//        Bitmap bitmap = null;
//
//        if (drawable instanceof BitmapDrawable) {
//            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//            if (bitmapDrawable.getBitmap() != null) {
//                return bitmapDrawable.getBitmap();
//            }
//        }
//
//        if (drawable.getIntrinsicWidth() <= 0
//                || drawable.getIntrinsicHeight() <= 0) {
//            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
//        } else {
//            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
//                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
//        }
//
//        Canvas canvas = new Canvas(bitmap);
//        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
//        drawable.draw(canvas);
//        return bitmap;
//    }
//
//    /**
//     * @param bitmap is an Bitmap object
//     * @see this method used to convert the bitmap into string conversion
//     */
//    public String BitMapToString(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] arr = baos.toByteArray();
//        String result = Base64.encodeToString(arr, Base64.DEFAULT);
//        return result;
//    }
//
//    public interface AppInfoInterface {
//
//        void onBackgroundTaskDataObtained(List<String> lockAppList,List<AppDetails> installedAppList );
//    }
//}
//
