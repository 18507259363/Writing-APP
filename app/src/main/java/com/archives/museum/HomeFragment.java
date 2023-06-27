package com.archives.museum;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.archives.museum.adpter.Home_Adpter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home,container,false);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ViewPager2 viewPager2=v.findViewById(R.id.main_body);
        Home_Adpter homeAdpter=new Home_Adpter();
        viewPager2.setAdapter(homeAdpter);

        return v;
    }

}