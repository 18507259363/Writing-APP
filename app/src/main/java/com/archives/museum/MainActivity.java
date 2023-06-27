package com.archives.museum;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    LinearLayout Home,Me,Igw,Find;

    ConstraintLayout Body;
    ImageView Himag,Meimag,Igwimag,Fidimag;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        QMUIStatusBarHelper.setStatusBarLightMode(this);
        setContentView(R.layout.activity_main);

        Body=findViewById(R.id.body);

        Home=findViewById(R.id.home); Himag=findViewById(R.id.home_imag);
        Me=findViewById(R.id.me); Meimag=findViewById(R.id.me_imag);
        Igw=findViewById(R.id.igw); Igwimag=findViewById(R.id.igw_imag);
        Find=findViewById(R.id.find); Fidimag=findViewById(R.id.find_imag);

        Home.setOnClickListener(Linck); Igw.setOnClickListener(Linck);
        Me.setOnClickListener(Linck); Find.setOnClickListener(Linck);

        navController= Navigation.findNavController(this,R.id.main_body);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                String name = (String) navDestination.getLabel();
                switch (name){
                    case "首页":
                        Body.setBackground(getDrawable(R.drawable.mainback));
                        Himag.setImageDrawable(getDrawable(R.drawable.home_ture));
                        Fidimag.setImageDrawable(getDrawable(R.drawable.find_false));
                        Igwimag.setImageDrawable(getDrawable(R.drawable.igw_l_edit));
                        Meimag.setImageDrawable(getDrawable(R.drawable.me_false));
                        break;
                    case "精选":
                        Body.setBackground(getDrawable(R.drawable.mainback));
                        Himag.setImageDrawable(getDrawable(R.drawable.home_false));
                        Fidimag.setImageDrawable(getDrawable(R.drawable.find_ture));
                        Igwimag.setImageDrawable(getDrawable(R.drawable.igw_l_edit));
                        Meimag.setImageDrawable(getDrawable(R.drawable.me_false));
                        break;
                    case "创作":
                        Body.setBackground(getDrawable(R.drawable.mainback));
                        Himag.setImageDrawable(getDrawable(R.drawable.home_false));
                        Fidimag.setImageDrawable(getDrawable(R.drawable.find_false));
                        Igwimag.setImageDrawable(getDrawable(R.drawable.igw_f_edit));
                        Meimag.setImageDrawable(getDrawable(R.drawable.me_false));
                        break;
                    case "我的":
                        Body.setBackground(getDrawable(R.drawable.me_bj));
                        Himag.setImageDrawable(getDrawable(R.drawable.home_false));
                        Fidimag.setImageDrawable(getDrawable(R.drawable.find_false));
                        Igwimag.setImageDrawable(getDrawable(R.drawable.igw_l_edit));
                        Meimag.setImageDrawable(getDrawable(R.drawable.me_ture));
                        break;
                }
            }
        });



    }

    View.OnClickListener Linck=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences preferences = getSharedPreferences("user",MODE_PRIVATE);
            switch (v.getId()){
                case R.id.home:
                    navController.navigate(R.id.homeFragment);
                    break;
                case R.id.find:
                    navController.navigate(R.id.findFragment);
                    break;
                case R.id.igw:
                    navController.navigate(R.id.igwFragment);
                    break;
                case R.id.me:
                    Bundle s=new Bundle();
                    s.putString("id",preferences.getString("uid",""));
                    s.putString("unm",preferences.getString("unme",""));
                    s.putString("uimg",preferences.getString("uimg",""));
                    MeFragment meFragment=new MeFragment();
                    meFragment.setArguments(s);
                    navController.navigate(R.id.meFragment2,s);
                    break;
            }
        }
    };



}