package com.bw.movie.adper.detalis_adper;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.movie_detail.ShortFilmList;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZUtils;
import cn.jzvd.JzvdStd;


public class ShortFilmAdper extends RecyclerView.Adapter<ShortFilmAdper.ShortFilmViewHolder> {
    List<ShortFilmList> shortFilmList = new ArrayList<>();


    public void addAll(List<ShortFilmList> shortFilmList) {
        this.shortFilmList.addAll(shortFilmList);
    }

    @NonNull
    @Override
    public ShortFilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.short_item, parent, false);
        return new ShortFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShortFilmViewHolder holder, int position) {
        ShortFilmList shortFilmList = this.shortFilmList.get(position);
        String videoUrl = shortFilmList.getVideoUrl();
        holder.mJcVideo.setUp(videoUrl,"");
        String imageUrl = shortFilmList.getImageUrl();
        Glide.with(holder.itemView.getContext()).load(imageUrl).into(holder.mJcVideo.posterImageView);
    }

    @Override
    public int getItemCount() {
        return shortFilmList.size();
    }

    public class ShortFilmViewHolder extends RecyclerView.ViewHolder {
        private JzvdStd mJcVideo;
        public ShortFilmViewHolder(@NonNull View itemView) {
            super(itemView);
            mJcVideo = (JzvdStd) itemView.findViewById(R.id.jc_Video);


        }
    }
}
