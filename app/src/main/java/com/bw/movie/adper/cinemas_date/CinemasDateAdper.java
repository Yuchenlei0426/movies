package com.bw.movie.adper.cinemas_date;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activity.CinemaDetailsActivity;
import com.bw.movie.bean.cinemas_date.CinemasDateResult;
import com.bw.movie.bean.evenbean.CineamaIdShow;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 *@describe(描述)：根据日期查询列表
 *@data（日期）: 2020/5/8
 *@time（时间）: 15:53
 *@author（作者）: 于晨雷
 **/
public class CinemasDateAdper extends RecyclerView.Adapter<CinemasDateAdper.CinemasDateViewHolder> {
    List<CinemasDateResult> result;

    public void addAll(List<CinemasDateResult> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public CinemasDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cinemasdate_item, parent, false);
        return new CinemasDateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemasDateViewHolder holder, int position) {
        CinemasDateResult cinemasDateResult = result.get(position);
//        影院名字
        String name = cinemasDateResult.getName();
//        影院地址
        String address = cinemasDateResult.getAddress();
//        影院的logo
        String logo = cinemasDateResult.getLogo();
//        设置
        holder.mSdvLogo.setImageURI(logo);
        holder.mTvName.setText(name);
        holder.mTvAddress.setText(address);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), CinemaDetailsActivity.class);
                int cinemaId = cinemasDateResult.getCinemaId();
                CineamaIdShow cineamaIdShow = new CineamaIdShow(cinemaId);
                EventBus.getDefault().postSticky(cineamaIdShow);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }


    public class CinemasDateViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mSdvLogo;
        private TextView mTvName;
        private TextView mTvAddress;

        public CinemasDateViewHolder(@NonNull View itemView) {
            super(itemView);
            mSdvLogo = (SimpleDraweeView) itemView.findViewById(R.id.sdv_logo);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mTvAddress = (TextView) itemView.findViewById(R.id.tv_address);
        }
    }
}
