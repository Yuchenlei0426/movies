package com.bw.movie.adper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activity.MovieDetailsActivity;
import com.bw.movie.bean.evenbean.MovieEvenShow;
import com.bw.movie.bean.home_hotmovie.HotResult;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class HotAdper extends RecyclerView.Adapter<HotAdper.HotViewHodler> {
    List<HotResult> mHotResults;


    public void addAll(List<HotResult> mHotResults) {
        this.mHotResults = mHotResults;
    }

    @NonNull
    @Override
    public HotViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_item, parent, false);
        return new HotViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotViewHodler holder, int position) {
        String imageUrl = mHotResults.get(position).getImageUrl();
        holder.ivHotImageUrl.setImageURI(imageUrl);
        String name = mHotResults.get(position).getName();
        holder.tvHotName.setText(name);
        double score = mHotResults.get(position).getScore();
        holder.tvHotScore.setText(score+"分");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), MovieDetailsActivity.class);
                int movieId = mHotResults.get(position).getMovieId();
                MovieEvenShow movieEvenShow = new MovieEvenShow();
                movieEvenShow.setMovieId(movieId);
                EventBus.getDefault().postSticky(movieEvenShow);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHotResults.size();
    }

    public class HotViewHodler extends RecyclerView.ViewHolder {
        private SimpleDraweeView ivHotImageUrl;
        private TextView tvHotScore;
        private TextView tvHotName;
        public HotViewHodler(@NonNull View itemView) {
            super(itemView);
            ivHotImageUrl = (SimpleDraweeView) itemView.findViewById(R.id.iv_hot_imageUrl);
            tvHotScore = (TextView) itemView.findViewById(R.id.tv_hot_score);
            tvHotName = (TextView) itemView.findViewById(R.id.tv_hot_name);
        }
    }
}
