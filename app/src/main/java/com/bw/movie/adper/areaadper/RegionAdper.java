package com.bw.movie.adper.areaadper;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.cinema_bean.findregion.FindRegionResult;
import com.bw.movie.bean.evenbean.AreaShow;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class RegionAdper extends RecyclerView.Adapter<RegionAdper.RegionViewHolder> {
    List<FindRegionResult> result;
    public  void addAll(List<FindRegionResult> result){
        this.result=result;
    }
    int priconCheck;
    @NonNull
    @Override
    public RegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.region_item, parent, false);
        return new RegionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionViewHolder holder, int position) {
        String regionName = result.get(position).getRegionName();
        holder.tv_area_name.setText(regionName);
        if (position==priconCheck) {
            holder.tv_area_name.setTextColor(Color.YELLOW);
        }else {
            holder.tv_area_name.setTextColor(Color.WHITE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int regionId = result.get(position).getRegionId();
                priconCheck =position;
                EventBus.getDefault().postSticky(new AreaShow(regionId));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class  RegionViewHolder extends RecyclerView.ViewHolder{
        TextView tv_area_name;
        public RegionViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_area_name = itemView.findViewById(R.id.tv_area_name);
        }
    }
}
