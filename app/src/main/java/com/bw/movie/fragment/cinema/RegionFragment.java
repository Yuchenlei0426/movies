package com.bw.movie.fragment.cinema;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.areaadper.AreaAdper;
import com.bw.movie.adper.areaadper.RegionAdper;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.bean.cinema_bean.findCinemaByRegion.CinemaByRegion;
import com.bw.movie.bean.cinema_bean.findCinemaByRegion.CinemaByRegionResult;
import com.bw.movie.bean.cinema_bean.findregion.FindRegionResult;
import com.bw.movie.bean.cinema_bean.findregion.FindRegionShow;
import com.bw.movie.bean.evenbean.AreaShow;
import com.bw.movie.bean.evenbean.MovieEvenShow;
import com.bw.movie.preantent.CPreantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @describe(描述)：区域
 * @data（日期）: 2020/5/4
 * @time（时间）: 16:33
 * @author（作者）: 于晨雷
 **/
public class RegionFragment extends BaseNetFragment {

    private RecyclerView mRvRegion;
    private RecyclerView mRvCinemabyregion;

    @Override
    protected int onLayout() {
        return R.layout.fragment_region;
    }

    @Override
    protected BasePreantent initPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView(View view) {
        initView(view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onData() {
        mCPreantent.onRegionData();
        mCPreantent.onCinemabyregionData(1);
    }

    @Subscribe(sticky = true)
    public void region(AreaShow areaShow) {
        int regionId = areaShow.getRegionId();
        mCPreantent.onCinemabyregionData(regionId);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof FindRegionShow) {
            List<FindRegionResult> result = ((FindRegionShow) o).getResult();
            RegionAdper regionAdper = new RegionAdper();
            regionAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            mRvRegion.setLayoutManager(linearLayoutManager);
            mRvRegion.setAdapter(regionAdper);

        }
        if (o instanceof CinemaByRegion) {
            List<CinemaByRegionResult> result = ((CinemaByRegion) o).getResult();
            AreaAdper areaAdper = new AreaAdper();
            areaAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL, false);
            mRvCinemabyregion.setLayoutManager(linearLayoutManager);
            mRvCinemabyregion.setAdapter(areaAdper);
        }
    }

    @Override
    public void onFail(String mes) {

    }

    private void initView(View view) {
        mRvRegion = (RecyclerView) view.findViewById(R.id.rv_region);
        mRvCinemabyregion = (RecyclerView) view.findViewById(R.id.rv_cinemabyregion);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
