package com.archives.museum;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

public class MeFragment extends Fragment {

    TextView uid,uname,uimg;
    ImageView uavatar;
    public MeFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_me,container,false);
        uid=v.findViewById(R.id.user_word);uname=v.findViewById(R.id.user_name);uavatar=v.findViewById(R.id.QMUIRadiusImageView);

        Bundle bundle=this.getArguments();
        if (bundle!=null){
            uid.setText("UID："+bundle.getString("id"));
            uname.setText(bundle.getString("unm"));
            Glide.with(this).load(bundle.getString("uimg")).into(uavatar);
        }
        ConstraintLayout personalInfo=v.findViewById(R.id.ca);
        personalInfo.setOnClickListener(vnc);
        return v;

    }
    View.OnClickListener vnc=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(),Me_Info.class);
            startActivity(intent);
        }
    };

    public void uidtxt(String str){
        if (uid!=null){
            uid.setText(str);
        }
    }
    public void unametxt(String str){
        if (uname!=null){
            uname.setText(str);
        }
    }

}