package com.international.languageswitchsdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Locale;

/**
 * 存储、获取语言的工具类
 */
public class PrefUtils {

    private static final String PREF = "spf";
    private static final String KEY_LANGUAGE = "app_language";

    /**
     *
     * 获得存入sp的语言
     * */
    public static String getLanguage(Context context) {
        if (TextUtils.isEmpty(getSpf(context, KEY_LANGUAGE))) {
            return Locale.getDefault().getLanguage();//context.getResources().getString(R.string.tv_english);
        }
        return getSpf(context, KEY_LANGUAGE);
    }

    /**
     * 语言存入sp
     * */
    public static void setLanguage(Context context, String value) {
        if (value == null) {
            value = "en";//context.getResources().getString(R.string.tv_english);
        }
        setSpf(context, KEY_LANGUAGE, value);
    }

    private static String getSpf(Context context, String key) {
        return context.getSharedPreferences(PREF, Context.MODE_PRIVATE).getString(key, "");
    }

    private static void setSpf(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        //SharedPreference 相关修改使用 apply 方法进行提交会先写入内存，然后异步写入磁盘，commit
        //方法是直接写入磁盘。如果频繁操作的话 apply 的性能会优于 commit，apply会将最后修改内容写入磁盘。
        //但是如果希望立刻获取存储操作的结果，并据此做相应的其他操作，应当使用 commit。
        editor.commit();
    }

}