package com.bw.movie.fragment.cinema_fragment;


import android.content.SharedPreferences;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.recommend.RecommendAdper;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.bean.cinema_bean.RecommendResult;
import com.bw.movie.bean.cinema_bean.RecommendShow;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.utils.App;

import java.util.List;

/**
 * @describe(描述)：推荐
 * @data（日期）: 2020/5/4
 * @time（时间）: 16:34
 * @author（作者）: 于晨雷
 **/
public class RecommendFragment extends BaseNetFragment {


    private RecyclerView mRvRecommend;

    @Override
    protected int onLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected BasePreantent initPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView(View view) {
        initView(view);
    }

    @Override
    protected void onData() {
        SharedPreferences mLogin = App.mLogin;
        String sessionId = mLogin.getString("sessionId", "");
        int userId = mLogin.getInt("userId", -1);
        mCPreantent.onRecommendData(userId, sessionId, 1, 5);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof RecommendShow) {
            List<RecommendResult> result = ((RecommendShow) o).getResult();
            RecommendAdper recommendAdper = new RecommendAdper();
            recommendAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            mRvRecommend.setLayoutManager(linearLayoutManager);
            mRvRecommend.setAdapter(recommendAdper);
        }
    }

    @Override
    public void onFail(String mes) {

    }

    private void initView(View view) {
        mRvRecommend = (RecyclerView) view.findViewById(R.id.rv_recommend);
    }
}
