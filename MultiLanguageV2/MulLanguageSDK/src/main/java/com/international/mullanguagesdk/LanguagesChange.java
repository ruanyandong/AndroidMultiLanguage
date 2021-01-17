package com.international.mullanguagesdk;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

/**
 * @author -> Wings
 * @date -> 2021/1/17
 * @email -> ruanyandongai@gmail.com 729368173@qq.com
 * @phone -> 18983790146
 * @blog -> https://ruanyandong.github.io https://blog.csdn.net/qq_34681580
 * 语种变化监听
 */
public class LanguagesChange implements ComponentCallbacks {

    /** 系统语种 */
    private static Locale sSystemLanguage;

    static {
        // 获取当前系统的语种
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sSystemLanguage = Resources.getSystem().getConfiguration().getLocales().get(0);
        } else  {
            sSystemLanguage = Resources.getSystem().getConfiguration().locale;
        }
    }

    /**
     * 获取系统的语种
     */
    public static Locale getSystemLanguage() {
        return sSystemLanguage;
    }

    /**
     * 注册系统语种变化监听
     */
    public static void register(Context context) {
        context.registerComponentCallbacks(new LanguagesChange());
    }

    /**
     * 手机的语种发生了变化
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // 更新记录的系统语种
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sSystemLanguage = newConfig.getLocales().get(0);
        } else  {
            sSystemLanguage = newConfig.locale;
        }
    }

    @Override
    public void onLowMemory() {}
}