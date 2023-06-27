package com.archives.museum;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.qmuiteam.qmui.util.QMUIColorHelper;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.qmuiteam.qmui.widget.QMUICollapsingTopBarLayout;

import java.util.Timer;
import java.util.TimerTask;

public class GActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setContentView(R.layout.activity_gactivity);
        initv();
    }

    private void initv() {
        SharedPreferences preferences = getSharedPreferences("user",MODE_PRIVATE);
        boolean sign = preferences.getBoolean("autho",false);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (sign==true){
                    maclass();
                }else {
                    loginclass();
                }
            }
        },3*1000);
    }

    private void loginclass() {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void maclass() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}