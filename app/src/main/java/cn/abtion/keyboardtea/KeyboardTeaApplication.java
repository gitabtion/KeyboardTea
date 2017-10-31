package cn.abtion.keyboardtea;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.abtion.keyboardtea.util.CacheUtil;

/**
 * @author abtion.
 * @since 17/10/23 20:35.
 * email caiheng@hrsoft.net.
 */

public class KeyboardTeaApplication extends Application {
    /** DEBUG模式标识 */
    public static final boolean DEBUG = BuildConfig.DEBUG;
    private static KeyboardTeaApplication instance;//实例对象
    private static List<Activity> activityList = new ArrayList<>();
    private static CacheUtil cacheUtil;
    public static Context appContext;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        appContext = this;
        initEM();
    }

    /**
     * 初始化环信
     */
    private void initEM() {
        int pid = android.os.Process.myPid();
        String processName = getProcessAppName(pid);
        if (processName == null || !processName.equalsIgnoreCase(appContext.getPackageName())) {
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        EMOptions options = new EMOptions();
        options.setAutoLogin(true);
        options.setAutoLogin(false);
        EMClient.getInstance().init(appContext, options);
        EMClient.getInstance().setDebugMode(DEBUG);
    }

    /**
     * 获取processAppName
     *
     * @param pID pid
     * @return name
     */
    private String getProcessAppName(int pID) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) appContext.getSystemService(ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) iterator.next();
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
    /**
     * 缓存初始化
     */
    public CacheUtil getCacheUtil() {
        if (cacheUtil == null) {
            cacheUtil = CacheUtil.get(instance.getFilesDir());
        }
        return cacheUtil;
    }

    /**
     * 外部获取实例对象
     *
     * @return net.hrsoft.transparent_factory_manager.NEUQerCCApplication
     */
    public static KeyboardTeaApplication getInstance() {
        return instance;
    }

    private static android.app.Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new android.app.Application.ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            activityList.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            activityList.remove(activity);
        }
    };

    /**
     * 移除Activity
     *
     * @param activity activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activity.finish();
        }
    }

    /**
     * 清除所有Activity
     */
    public void removeAllActivity() {
        for (Activity activity : activityList) {
            if (activity != null && !activity.isFinishing()){
                activity.finish();
            }
        }
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        removeAllActivity();
        System.exit(0);
    }

    /**
     * 退出账户
     */
    public void exitAccount() {
        // TODO: 17/10/23 退出账户后的相关操作
    }
}
