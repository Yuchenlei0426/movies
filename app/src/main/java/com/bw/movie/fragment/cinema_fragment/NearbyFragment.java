package com.bw.movie.fragment.cinema_fragment;


import android.content.SharedPreferences;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.nearby.NearbyCinemasAdper;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.bean.cinema_bean.nearbycinemas.NearbyCinemas;
import com.bw.movie.bean.cinema_bean.nearbycinemas.NearbyCinemasResult;
import com.bw.movie.bean.postion.District;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.utils.App;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @describe(描述)：附近
 * @data（日期）: 2020/5/4
 * @time（时间）: 16:44
 * @author（作者）: 于晨雷
 **/
public class NearbyFragment extends BaseNetFragment {


    private double mLatitude;
    private double mLongitude;
    private RecyclerView mRvNearBy;

    @Override
    protected int onLayout() {
        return R.layout.fragment_nearby;
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

    @Subscribe(sticky = true)
    public void onShow(District district) {
        mLatitude = district.getLatitude();
        mLongitude = district.getLongitude();
    }

    @Override
    protected void onData() {
        SharedPreferences mLogin = App.mLogin;
        String sessionId = mLogin.getString("sessionId", "");
        int userId = mLogin.getInt("userId", -1);
        mCPreantent.onNearbyData(userId, sessionId, mLongitude, mLatitude, 1,5);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof NearbyCinemas) {
            List<NearbyCinemasResult> result = ((NearbyCinemas) o).getResult();
            NearbyCinemasAdper nearbyCinemasAdper = new NearbyCinemasAdper();
            nearbyCinemasAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            mRvNearBy.setLayoutManager(linearLayoutManager);
            mRvNearBy.setAdapter(nearbyCinemasAdper);
        }
    }

    @Override
    public void onFail(String mes) {

    }

    private void initView(View view) {
        mRvNearBy = (RecyclerView) view.findViewById(R.id.rv_nearBy);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
