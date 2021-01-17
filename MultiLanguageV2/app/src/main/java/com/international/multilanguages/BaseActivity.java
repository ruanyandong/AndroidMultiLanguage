package com.international.multilanguages;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.international.mullanguagesdk.MultiLanguages;

/**
 * @author -> Wings
 * @date -> 2021/1/17
 * @email -> ruanyandongai@gmail.com 729368173@qq.com
 * @phone -> 18983790146
 * @blog -> https://ruanyandong.github.io https://blog.csdn.net/qq_34681580
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(MultiLanguages.attach(newBase));
    }
}