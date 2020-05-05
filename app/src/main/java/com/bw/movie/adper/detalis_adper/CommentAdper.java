package com.bw.movie.adper.detalis_adper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.comment_details.Commentresult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentAdper extends RecyclerView.Adapter<CommentAdper.CommentViewHolder> {
    List<Commentresult> result;

    public void addAll(List<Commentresult> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Commentresult commentresult = result.get(position);
        String commentHeadPic = commentresult.getCommentHeadPic();
        holder.mSdvHead.setImageURI(commentHeadPic);
        String commentContent = commentresult.getCommentContent();
        holder.mTvCommentContent.setText(commentContent);
        String commentUserName = commentresult.getCommentUserName();
        holder.mTvCommName.setText(commentUserName);
//      时间
        long commentTime = commentresult.getCommentTime();
        Date date = new Date(commentTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
        holder.mTvDate.setText(dateFormat.format(date));

        double score = commentresult.getScore();
        holder.mTvGraded.setText(score+"分");
        int greatNum = commentresult.getGreatNum();
        holder.mTvPraiseNum.setText(greatNum+"");
        int replyNum = commentresult.getReplyNum();
        holder.mTvMany.setText("等"+replyNum+"人进行了回复");


        List<String> replyHeadPic = commentresult.getReplyHeadPic();
        ReplyAdper replyAdper = new ReplyAdper();
        replyAdper.addAll(replyHeadPic);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.itemView.getContext(), RecyclerView.HORIZONTAL, false);
        holder.mRvReplyHeadPic.setLayoutManager(linearLayoutManager);
        holder.mRvReplyHeadPic.setAdapter(replyAdper);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }


    public class CommentViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView mSdvHead;
        TextView mTvCommName;
        TextView mTvGraded;
        TextView mTvDate;
        CheckBox mBoxPraise;
        TextView mTvPraiseNum;
        RecyclerView mRvReplyHeadPic;
        TextView mTvMany;
        TextView mTvCommentContent;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            mSdvHead = (SimpleDraweeView) itemView.findViewById(R.id.sdv_head);
            mTvCommName = (TextView) itemView.findViewById(R.id.tv_comm_name);
            mTvGraded = (TextView) itemView.findViewById(R.id.tv_graded);
            mTvDate = (TextView) itemView.findViewById(R.id.tv_date);
            mBoxPraise = (CheckBox) itemView.findViewById(R.id.box_praise);
            mTvPraiseNum = (TextView) itemView.findViewById(R.id.tv_praiseNum);
            mRvReplyHeadPic = (RecyclerView) itemView.findViewById(R.id.rv_replyHeadPic);
            mTvCommentContent = (TextView) itemView.findViewById(R.id.tv_commentContent);
            mTvMany = (TextView) itemView.findViewById(R.id.tv_many);
        }
    }
}
