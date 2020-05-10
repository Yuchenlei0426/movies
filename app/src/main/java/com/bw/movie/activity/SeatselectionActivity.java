package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adper.movie_hall.MovieScheduleAdper;
import com.bw.movie.adper.seatInfo.SeatInfoAdper;
import com.bw.movie.base.BaseNetActivity;
import com.bw.movie.bean.evenbean.CineamaIdShow;
import com.bw.movie.bean.evenbean.ScheduleEvent;
import com.bw.movie.bean.evenbean.SeatseleCineamaIdShow;
import com.bw.movie.bean.movieschedule.MovieSchedule;
import com.bw.movie.bean.movieschedule.MovieScheduleShow;
import com.bw.movie.bean.seatInfo.SaetInfoShow;
import com.bw.movie.bean.seatInfo.SeatInfo;
import com.bw.movie.preantent.CPreantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SeatselectionActivity extends BaseNetActivity {
    private static final String TAG = "SeatselectionActivity";
    private int mMovieId;
    private androidx.recyclerview.widget.RecyclerView mSeatRv;
    private androidx.recyclerview.widget.RecyclerView mRvFindMovieSchedule;
    private android.widget.Button mButOrder;
    private android.widget.Button mButRoom;
    private int mCinemaId;
    private MovieScheduleAdper mMovieScheduleAdper;
    int scheduleId;
    private int cinemaId;
    private int movieId;
    int hall;
    double fare1;
    double money;
    private int mHallId;
    private double mFare;
    private int mId;
    private String mFormat;
    private ArrayList<String> mStrings;
    private PopupWindow mPopupWindow;

    @Override
    protected int onlayout() {
        return R.layout.activity_seatselection;
    }

    @Override
    protected CPreantent onPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView() {
        initView();
        EventBus.getDefault().register(this);
//        but_order
        mButRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Subscribe(sticky = true)
    public void cinemaIdShow(SeatseleCineamaIdShow seatseleCineamaIdShow) {
          mCinemaId = seatseleCineamaIdShow.getCinemaId();

    }

    @Subscribe(sticky = true)
    public void ScheduleEventShow(ScheduleEvent scheduleEvent) {
           mMovieId = scheduleEvent.getMovieId();

    }

    @Override
    protected void onData() {
        //    根据电影ID 和影院ID 查询影厅
        mCPreantent.onFindMovieScheduleData(mMovieId,mCinemaId);
        mStrings = new ArrayList<>();
        //    根据影厅ID 查询座位

    }


    @Override
    public void onSuccess(Object o) {

        //    根据电影ID 和影院ID 查询影厅
        if (o instanceof MovieScheduleShow) {
            List<MovieSchedule> result = ((MovieScheduleShow) o).getResult();
            if (result==null){
                Toast.makeText(SeatselectionActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
            }else {
                mMovieScheduleAdper = new MovieScheduleAdper();
                mMovieScheduleAdper.addAll(result);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SeatselectionActivity.this, RecyclerView.HORIZONTAL, false);
                mRvFindMovieSchedule.setLayoutManager(linearLayoutManager);
                mRvFindMovieSchedule.setAdapter(mMovieScheduleAdper);
                mMovieScheduleAdper.setOnClickListener(new MovieScheduleAdper.OnClickListener() {
                    @Override
                    public void onSeatClicked(int hallId, double fare, int id) {
                        hall = hallId;
                        mCPreantent.onFindSeatInfoData(hallId);
                        fare1 = fare;
                        money = 0.0;
                        mButRoom.setText("￥ " + money);
                        scheduleId = id;
                    }
                });
                for (int i = 0; i < result.size(); i++) {
                    mHallId = ((MovieScheduleShow) o).getResult().get(i).getHallId();
                    mCPreantent.onFindSeatInfoData(mMovieId);
                    mFare = ((MovieScheduleShow) o).getResult().get(i).getFare();
                    money=0;
                    mButRoom.setText("￥ " + money);
                    mId = ((MovieScheduleShow) o).getResult().get(i).getId();
                }
            }
        }
        //    根据影厅ID 查询座位
        if (o instanceof SaetInfoShow) {
            List<SeatInfo> result = ((SaetInfoShow) o).getResult();
            if (result!=null) {
                SeatInfoAdper seatInfoAdper = new SeatInfoAdper();
                seatInfoAdper.addAll(result);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SeatselectionActivity.this, RecyclerView.VISIBLE, false);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(SeatselectionActivity.this, 5, RecyclerView.VERTICAL, false);
                mSeatRv.setLayoutManager(gridLayoutManager);
                mSeatRv.setAdapter(seatInfoAdper);
                seatInfoAdper.setSetOnclickListener(new SeatInfoAdper.setOnclickListener() {

                    private String soft;

                    @Override
                    public void onClicked(int row, int seat, boolean isChecked) {
                        if (isChecked == true) {
                            soft = row + "-" + seat;
                            mStrings.add(soft);
                            Toast.makeText(SeatselectionActivity.this, row + "排" + seat + "座", Toast.LENGTH_SHORT).show();
                            money = money + fare1;
                            DecimalFormat df = new DecimalFormat("0.00");
                            mFormat = df.format(money);
                            mButRoom.setText("￥ " + mFormat);
                        } else {
                            soft = row + "-" + seat;
                            mStrings.remove(soft);
                            money = money - fare1;
                            DecimalFormat df = new DecimalFormat("0.00");
                            String format = df.format(money);
                            mButRoom.setText("￥" + format);
                        }
                    }
                });
            }else {
                Toast.makeText(SeatselectionActivity.this, "无数据", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: "+mes);
    }

    private void initView() {
        mSeatRv = (RecyclerView) findViewById(R.id.seat_rv);
        mRvFindMovieSchedule = (RecyclerView) findViewById(R.id.rv_findMovieSchedule);
        mButOrder = (Button) findViewById(R.id.but_order);
        mButRoom = (Button) findViewById(R.id.but_room);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initPopWindow(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_popip, null, false);

        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        mPopupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画
        mPopupWindow.setTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        mPopupWindow.showAsDropDown(v, 50, 50);

        //设置popupWindow里的按钮的事件

    }
}
