package com.bw.movie.adper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;

import java.util.List;

/**
 *@describe(描述)：日期适配器
 *@data（日期）: 2020/5/8
 *@time（时间）: 15:56
 *@author（作者）: 于晨雷
 **/
public class DateAdper extends RecyclerView.Adapter<DateAdper.DateViewHoler> {
    private List<String> result;

    public void addAll(List<String> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public DateViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_item, parent, false);
        return new DateViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DateViewHoler holder, int position) {
        String date = result.get(position);
        holder.mTvDates.setText(date);
        holder.mTvDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = result.get(position);
                mOnClickListener.onClick(date);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }


    public class DateViewHoler extends RecyclerView.ViewHolder {
        TextView mTvDates;

        public DateViewHoler(@NonNull View itemView) {
            super(itemView);
            mTvDates = (TextView) itemView.findViewById(R.id.tv_dates);
        }
    }

   private OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

   public interface OnClickListener {
        void onClick(String date);
    }
}
