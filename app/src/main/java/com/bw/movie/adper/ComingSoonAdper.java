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
import com.bw.movie.bean.home_comingsoonmovie.ComingSoonResult;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ComingSoonAdper extends RecyclerView.Adapter<ComingSoonAdper.ComingSoonViewHodler> {
    List<ComingSoonResult> mComingSoonResults;

    public void addAll(List<ComingSoonResult> mComingSoonResults){
        this.mComingSoonResults=mComingSoonResults;
    }

    @NonNull
    @Override
    public ComingSoonViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coming_item, parent, false);
        return new ComingSoonViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComingSoonViewHodler holder, int position) {
        ComingSoonResult comingSoonResult = mComingSoonResults.get(position);
        String imageUrl = comingSoonResult.getImageUrl();
        holder.sdvImageUrl.setImageURI(imageUrl);
        String name = comingSoonResult.getName();
        holder.tvComingName.setText(name);
        long releaseTime = comingSoonResult.getReleaseTime();
        Date date = new Date(releaseTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        holder.tvComingReleaseTime.setText(dateFormat.format(date)+"上映");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), MovieDetailsActivity.class);
                int movieId = mComingSoonResults.get(position).getMovieId();
                MovieEvenShow movieEvenShow = new MovieEvenShow();
                movieEvenShow.setMovieId(movieId);
                EventBus.getDefault().postSticky(movieEvenShow);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mComingSoonResults.size();
    }


    public class ComingSoonViewHodler extends RecyclerView.ViewHolder {
         SimpleDraweeView sdvImageUrl;
         TextView tvComingName;
         TextView tvComingReleaseTime;
         TextView tvComingWantSeeNum;
        public ComingSoonViewHodler(@NonNull View itemView) {
            super(itemView);
            sdvImageUrl = (SimpleDraweeView) itemView.findViewById(R.id.sdv_imageUrl);
            tvComingName = (TextView) itemView.findViewById(R.id.tv_coming_name);
            tvComingReleaseTime = (TextView) itemView.findViewById(R.id.tv_coming_releaseTime);
            tvComingWantSeeNum = (TextView) itemView.findViewById(R.id.tv_coming_wantSeeNum);

        }
    }
}
