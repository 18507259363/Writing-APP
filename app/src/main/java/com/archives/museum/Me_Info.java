package com.archives.museum;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.archives.museum.databinding.ActivityMeinfoBinding;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class Me_Info extends AppCompatActivity {

    ImageView uimge;
    TextView uname,uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meinfo);
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        iniv();
    }


    public void iniv(){
        String uinfid,unifname,unifimage;
        SharedPreferences preferences = getSharedPreferences("user",MODE_PRIVATE);
        unifimage=preferences.getString("uimg","");uinfid=preferences.getString("uid","");unifname=preferences.getString("unme","");
        uimge=findViewById(R.id.uinfoimge);uid=findViewById(R.id.uinfoid);uname=findViewById(R.id.uinfoname);
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(Me_Info.this).load(unifimage).into(uimge);
                        uid.setText(uinfid);uname.setText(unifname);
                    }
                });
            }
        }).start();
    }
    
}