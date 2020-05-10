package com.bw.movie.fragment.select_fragment;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.preantent.CPreantent;

/**
 * @describe(描述)：最低价格
 * @data（日期）: 2020/5/8
 * @time（时间）: 0:42
 * @author（作者）: 于晨雷
 **/
public class BottomPriceFragment extends BaseNetFragment {


    private RecyclerView mRvBottomPrice;

    @Override
    protected int onLayout() {
        return R.layout.fragment_bottom_price;
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

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFail(String mes) {

    }

    private void initView(View view) {
        mRvBottomPrice = (RecyclerView) view.findViewById(R.id.rv_bottom_price);
    }
}
