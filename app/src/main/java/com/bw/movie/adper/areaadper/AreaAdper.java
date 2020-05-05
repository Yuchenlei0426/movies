package com.bw.movie.adper.areaadper;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.cinema_bean.findCinemaByRegion.CinemaByRegionResult;

import java.util.List;

public class AreaAdper extends RecyclerView.Adapter<AreaAdper.AreaViewHolder> {
    List<CinemaByRegionResult> result;
    public void addAll(List<CinemaByRegionResult> result){
        this.result=result;
    }
    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.area_item, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        String name = result.get(position).getName();
        holder.tv_name.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Intent intent = new Intent(holder.itemView.getContext(), CinemaDetailsActivity.class);
                holder.itemView.getContext().startActivity(intent);

//                        详情CinemaDetailsActivity  附近nearby*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class AreaViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
