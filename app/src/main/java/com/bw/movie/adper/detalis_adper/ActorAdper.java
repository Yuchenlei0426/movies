package com.bw.movie.adper.detalis_adper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.movie_detail.MovieActor;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ActorAdper extends RecyclerView.Adapter<ActorAdper.ActorViewHolder> {
    List<MovieActor> movieActor;
    public void addAll(List<MovieActor> movieActor){
        this.movieActor=movieActor;
    }
    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_item, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        MovieActor movieActor = this.movieActor.get(position);
        String photo = movieActor.getPhoto();
        holder.mSdvActor.setImageURI(photo);
    }

    @Override
    public int getItemCount() {
        return movieActor.size();
    }

    public class ActorViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mSdvActor;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            mSdvActor = (SimpleDraweeView) itemView.findViewById(R.id.sdv_actor);
        }
    }
}
