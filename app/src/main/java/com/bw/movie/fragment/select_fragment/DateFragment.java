package com.bw.movie.fragment.select_fragment;


import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.cinemas_date.CinemasDateAdper;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.bean.cinemas_date.CinemasDateResult;
import com.bw.movie.bean.cinemas_date.CinemasDateShow;
import com.bw.movie.bean.evenbean.DateEventShow;
import com.bw.movie.bean.evenbean.MovieEvenShow;
import com.bw.movie.bean.evenbean.MovieIdShow;
import com.bw.movie.preantent.CPreantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @describe(描述)：根据日期查询影院
 * @data（日期）: 2020/5/7
 * @time（时间）: 16:38
 * @author（作者）: 于晨雷
 **/
public class DateFragment extends BaseNetFragment {

    private static final String TAG = "DateFragment";
    private int mMovieId;
    private RecyclerView mRvCinemaDate;
    private String mDate;

    @Override
    protected int onLayout() {
        return R.layout.fragment_date;
    }

    @Override
    protected BasePreantent initPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView(View view) {
        initView(view);
        EventBus.getDefault().register(this);

    }

    @Subscribe(sticky = true)
    public void onShow(MovieIdShow movieIdShow) {
        mMovieId = movieIdShow.getMovieId();
    }

    @Subscribe(sticky = true)
    public void onDateShow(DateEventShow dateEventShow) {
        boolean b = EventBus.getDefault().removeStickyEvent(dateEventShow);
        if (b) {
            mDate = dateEventShow.getDate();
        }

    }

    @Override
    protected void onData() {
        Toast.makeText(getContext(), mDate, Toast.LENGTH_SHORT).show();
        mCPreantent.onCinemasinfobydateData(mMovieId, mDate, 1, 5);

    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof CinemasDateShow) {
            List<CinemasDateResult> result = ((CinemasDateShow) o).getResult();
            CinemasDateAdper cinemasDateAdper = new CinemasDateAdper();
            cinemasDateAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            mRvCinemaDate.setLayoutManager(linearLayoutManager);
            mRvCinemaDate.setAdapter(cinemasDateAdper);
        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: "+mes);
    }



    private void initView(View view) {
        mRvCinemaDate = (RecyclerView) view.findViewById(R.id.rv_cinemaDate);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
