package com.bw.movie.adper.detalis_adper;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.activity.ImageViewActivity;
import com.bw.movie.bean.evenbean.ImagesShow;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class StillAdper extends RecyclerView.Adapter<StillAdper.StillViewHolder> {
    private static final String TAG = "StillAdper";
    List<String> posterList;

    public void addAll(List<String> posterList) {
        this.posterList = posterList;
    }

    @NonNull
    @Override
    public StillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.still_item, parent, false);
        return new StillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StillViewHolder holder, int position) {

        String images = posterList.get(position);
        Log.i(TAG, "onBindViewHolder: "+images);
        holder.mSdView.setImageURI(images);
        holder.mSdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ImageViewActivity.class);
                ImagesShow imagesShow = new ImagesShow();
                imagesShow.setImages(images);
                EventBus.getDefault().postSticky(imagesShow);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }



    public class StillViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mSdView;
        public StillViewHolder(@NonNull View itemView) {
            super(itemView);
            mSdView = (SimpleDraweeView) itemView.findViewById(R.id.sd_view);


        }
    }
}
