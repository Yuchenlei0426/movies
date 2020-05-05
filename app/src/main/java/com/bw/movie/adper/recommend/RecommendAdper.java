package com.bw.movie.adper.recommend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.cinema_bean.RecommendResult;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
/**
 *@describe(描述)：推荐影院适配器
 *@data（日期）: 2020/5/4
 *@time（时间）: 16:41
 *@author（作者）: 于晨雷
 **/
public class RecommendAdper extends RecyclerView.Adapter<RecommendAdper.RecommendViewHolder> {
    private List<RecommendResult> result;
    public void addAll(List<RecommendResult> result) {
        this.result=result;
    }

    public List<RecommendResult> getResult() {
        return result;
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, parent,false);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder holder, int position) {
        String logo = result.get(position).getLogo();
        holder.logo.setImageURI(logo);
        String name = result.get(position).getName();
        holder.tv_ct_name.setText(name);
        String address = result.get(position).getAddress();
        holder.tv_ct_address.setText(address);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView logo;
        TextView tv_ct_name, tv_ct_address;

        public RecommendViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logo);
            tv_ct_name = itemView.findViewById(R.id.tv_ct_name);
            tv_ct_address = itemView.findViewById(R.id.tv_ct_address);
        }
    }
}
