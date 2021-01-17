package com.international.multilanguages;

import android.app.Application;
import android.content.Context;

import com.international.mullanguagesdk.MultiLanguages;

/**
 * @author -> Wings
 * @date -> 2021/1/17
 * @email -> ruanyandongai@gmail.com 729368173@qq.com
 * @phone -> 18983790146
 * @blog -> https://ruanyandong.github.io https://blog.csdn.net/qq_34681580
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 在 Application 中初始化,监听语言变化
        MultiLanguages.register(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(MultiLanguages.attach(base));
    }
}