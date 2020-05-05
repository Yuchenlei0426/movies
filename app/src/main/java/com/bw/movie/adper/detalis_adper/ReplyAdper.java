package com.bw.movie.adper.detalis_adper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ReplyAdper extends RecyclerView.Adapter<ReplyAdper.ReplyViewHolder> {
    private List<String> replyHeadPic;

    public void addAll(List<String> replyHeadPic) {
        this.replyHeadPic = replyHeadPic;
    }

    @NonNull
    @Override
    public ReplyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reply_item, parent, false);
        return new ReplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyViewHolder holder, int position) {
        String reply = replyHeadPic.get(position);
        holder.mSdvReplyHeadPic.setImageURI(reply);
    }

    @Override
    public int getItemCount() {
        return replyHeadPic.size();
    }


    public class ReplyViewHolder extends RecyclerView.ViewHolder {
         SimpleDraweeView mSdvReplyHeadPic;
        public ReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            mSdvReplyHeadPic = (SimpleDraweeView) itemView.findViewById(R.id.sdv_ReplyHeadPic);
        }
    }
}
