package com.bw.movie.adper.areaadper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaDetailsActivity;
import com.bw.movie.bean.cinema_bean.findCinemaByRegion.CinemaByRegionResult;
import com.bw.movie.bean.evenbean.CineamaIdShow;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 *@describe(描述)：影院
 *@data（日期）: 2020/5/8
 *@time（时间）: 15:18
 *@author（作者）: 于晨雷
 **/
public class AreaAdper extends RecyclerView.Adapter<AreaAdper.AreaViewHolder> {
    List<CinemaByRegionResult> result;
    public void addAll(List<CinemaByRegionResult> result){
        this.result=result;
    }
    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.area_item, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        String name = result.get(position).getName();
        holder.tv_name.setText(name);
//        点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), CinemaDetailsActivity.class);
                int id = result.get(position).getId();
                CineamaIdShow cineamaIdShow = new CineamaIdShow(id);
                EventBus.getDefault().postSticky(cineamaIdShow);
                holder.itemView.getContext().startActivity(intent);
//                        详情CinemaDetailsActivity  附近nearby
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class AreaViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
