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
import com.bw.movie.bean.home_release.ReleaseResult;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe(描述)：正在热映
 * @data（日期）: 2020/4/19
 * @time（时间）: 18:54
 * @author（作者）: 于晨雷
 **/
public class ReleaseAdper extends RecyclerView.Adapter<ReleaseAdper.ReleaseViewHodler> {

    List<ReleaseResult> mReleaseResults = new ArrayList<>();


    public void onReleaseAddAll(List<ReleaseResult> mReleaseResults) {
        this.mReleaseResults.addAll(mReleaseResults);
    }

    @NonNull
    @Override
    public ReleaseViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.release_item, parent, false);
        return new ReleaseViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReleaseViewHodler holder, int position) {
        String imageUrl = mReleaseResults.get(position).getImageUrl();
        holder.ivImageUrl.setImageURI(imageUrl);
        String name = mReleaseResults.get(position).getName();
        holder.name.setText(name);
        double score = mReleaseResults.get(position).getScore();
        holder.tvScore.setText(score+"分");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), MovieDetailsActivity.class);
                int movieId = mReleaseResults.get(position).getMovieId();
                MovieEvenShow movieEvenShow = new MovieEvenShow();
                movieEvenShow.setMovieId(movieId);
                EventBus.getDefault().postSticky(movieEvenShow);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mReleaseResults.size();
    }

    public class ReleaseViewHodler extends RecyclerView.ViewHolder {
        private SimpleDraweeView ivImageUrl;
        private TextView tvScore;
        private TextView name;
        public ReleaseViewHodler(@NonNull View itemView) {
            super(itemView);
            ivImageUrl = (SimpleDraweeView) itemView.findViewById(R.id.iv_imageUrl);
            tvScore = (TextView) itemView.findViewById(R.id.tv_score);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
