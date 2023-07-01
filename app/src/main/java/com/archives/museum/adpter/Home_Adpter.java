package com.archives.museum.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.archives.museum.R;
import com.archives.museum.http.Http;
import com.bumptech.glide.request.transition.Transition;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Home_Adpter extends RecyclerView.Adapter<Home_Adpter.ViewPagerViewHoder> {
    @NonNull
    @Override
    public ViewPagerViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPagerViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_body,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        Http.get("http://localhost", new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String res=response.body().toString();
            }
        });
        return 3;
    }
    class ViewPagerViewHoder extends RecyclerView.ViewHolder{
        TextView word,date,day,writer;
        ConstraintLayout constraintLayout;
        public ViewPagerViewHoder(@NonNull View itemView) {
            super(itemView);
            constraintLayout=itemView.findViewById(R.id.home_body);
            word=itemView.findViewById(R.id.word);date=itemView.findViewById(R.id.date);
            day=itemView.findViewById(R.id.day);writer=itemView.findViewById(R.id.writer);
        }
    }
}
