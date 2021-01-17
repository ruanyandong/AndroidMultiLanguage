package com.international.mullanguagesdk;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

/**
 * @author -> Wings
 * @date -> 2021/1/17
 * @email -> ruanyandongai@gmail.com 729368173@qq.com
 * @phone -> 18983790146
 * @blog -> https://ruanyandong.github.io https://blog.csdn.net/qq_34681580
 *
 * 语言国际化工具类
 */
public class LanguagesUtils {
    /**
     * 判断上下文的语种和某个语种是否相同
     */
    public static boolean equalsLanguages(Context context, Locale locale) {
        Configuration config = context.getResources().getConfiguration();

        // API 版本兼容
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return config.getLocales().get(0).equals(locale);
        } else {
            return config.locale.equals(locale);
        }
    }

    /**
     * 更新当前 App 的语种
     */
    public static Context updateLanguages(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration config = new Configuration(resources.getConfiguration());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocaleList localeList = new LocaleList(locale);
                LocaleList.setDefault(localeList);
                config.setLocales(localeList);
            } else {
                config.setLocale(locale);
            }
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
            resources.updateConfiguration(config, resources.getDisplayMetrics());
        }
        Locale.setDefault(locale);
        return context;
    }

    /**
     * 获取某个语种下的 Resources 对象
     */
    public static Resources getLanguageResources(Context context, Locale locale) {
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocaleList localeList = new LocaleList(locale);
                LocaleList.setDefault(localeList);
                config.setLocales(localeList);
            } else {
                config.setLocale(locale);
            }
            return context.createConfigurationContext(config).getResources();
        } else {
            config.locale = locale;
            return new Resources(context.getAssets(), context.getResources().getDisplayMetrics(), config);
        }
    }
}
