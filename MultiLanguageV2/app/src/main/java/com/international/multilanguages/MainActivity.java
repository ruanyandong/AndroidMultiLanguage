package com.international.multilanguages;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.international.mullanguagesdk.MultiLanguages;

import java.util.Locale;

public class MainActivity extends BaseActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_language_auto).setOnClickListener(this);
        findViewById(R.id.btn_language_cn).setOnClickListener(this);
        findViewById(R.id.btn_language_tw).setOnClickListener(this);
        findViewById(R.id.btn_language_en).setOnClickListener(this);

        ((TextView) findViewById(R.id.tv_language_system)).setText(MultiLanguages.getLanguageString(this, MultiLanguages.getSystemLanguage(), R.string.current_language));

        Locale locale = MultiLanguages.getAppLanguage(this);
        if (MultiLanguages.equalsCountry(locale, Locale.CHINA)) {
            Toast.makeText(this, "简体", Toast.LENGTH_SHORT).show();
        } else if (MultiLanguages.equalsCountry(locale, Locale.TAIWAN)) {
            Toast.makeText(this, "繁体", Toast.LENGTH_SHORT).show();
        } else if (MultiLanguages.equalsLanguage(locale, Locale.ENGLISH)) {
            Toast.makeText(this, "英语", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "未知语种", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * {@link android.view.View.OnClickListener}
     */
    @Override
    public void onClick(View v) {
        // 是否需要重启
        boolean restart;
        switch (v.getId()) {
            // 跟随系统
            case R.id.btn_language_auto:
                restart = MultiLanguages.setSystemLanguage(this);
                break;
            // 简体中文
            case R.id.btn_language_cn:
                restart = MultiLanguages.setAppLanguage(this, Locale.CHINA);
                break;
            // 繁体中文
            case R.id.btn_language_tw:
                restart = MultiLanguages.setAppLanguage(this, Locale.TAIWAN);
                break;
            // 英语
            case R.id.btn_language_en:
                restart = MultiLanguages.setAppLanguage(this, Locale.ENGLISH);
                break;
            default:
                restart = false;
                break;
        }

        if (restart) {
            // 1.使用recreate来重启Activity的效果差，有闪屏的缺陷
             //recreate();

            // 2.使用常规startActivity来重启Activity，有从左向右的切换动画，稍微比recreate的效果好一点，但是这种并不是最佳的效果
            // startActivity(new Intent(this, LanguageActivity.class));
            // finish();

            // 3.我们可以充分运用 Activity 跳转动画，在跳转的时候设置一个渐变的效果，相比前面的两种带来的体验是最佳的
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }
    }
}