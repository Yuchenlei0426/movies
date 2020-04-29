package com.bw.movie.adper.detalis_adper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.movie_detail.MovieDirector;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class DirectorAdper extends RecyclerView.Adapter<DirectorAdper.DirectorViewHodler> {
    List<MovieDirector> movieDirector;


    public void addAll(List<MovieDirector> movieDirector) {
        this.movieDirector = movieDirector;
    }

    @NonNull
    @Override
    public DirectorViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.director_item, parent, false);
        return new DirectorViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectorViewHodler holder, int position) {
        MovieDirector movieDirector = this.movieDirector.get(position);
        String photo = movieDirector.getPhoto();
        holder.mSdvDirector.setImageURI(photo);
    }

    @Override
    public int getItemCount() {
        return movieDirector.size();
    }



    public class DirectorViewHodler extends RecyclerView.ViewHolder {
        SimpleDraweeView mSdvDirector;
         TextView mTvName;

        public DirectorViewHodler(@NonNull View itemView) {
            super(itemView);
            mSdvDirector = (SimpleDraweeView) itemView.findViewById(R.id.sdv_director);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);

        }
    }
}
