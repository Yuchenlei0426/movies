package com.bw.movie.activity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adper.DateAdper;
import com.bw.movie.adper.fragment_adper.SelectFragmentAdper;
import com.bw.movie.base.BaseNetActivity;
import com.bw.movie.bean.date.DateShow;
import com.bw.movie.bean.evenbean.DateEventShow;
import com.bw.movie.fragment.cinema_fragment.RegionFragment;
import com.bw.movie.fragment.select_fragment.BottomPriceFragment;
import com.bw.movie.fragment.select_fragment.DateFragment;
import com.bw.movie.preantent.CPreantent;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
/**
 *@describe(描述)：影院
 *@data（日期）: 2020/5/8
 *@time（时间）: 15:55
 *@author（作者）: 于晨雷
 **/
public class SelectTheatersActivity extends BaseNetActivity {

    private static final String TAG = "SelectTheatersActivity";
    private com.google.android.material.tabs.TabLayout mTabSelect;
    private androidx.viewpager.widget.ViewPager mVpSelect;
    ArrayList<Fragment> fragments = new ArrayList<>();
    private RecyclerView mRv_date;
    private PopupWindow mPopWindow;


    @Override
    protected int onlayout() {
        return R.layout.activity_select_theaters;
    }

    @Override
    protected CPreantent onPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView() {
        initView();
    }

    @Override
    protected void onData() {
        fragments.add(new RegionFragment());
        fragments.add(new DateFragment());
        fragments.add(new BottomPriceFragment());
        SelectFragmentAdper selectFragmentAdper = new SelectFragmentAdper(getSupportFragmentManager());
        selectFragmentAdper.addAll(fragments);
        mVpSelect.setAdapter(selectFragmentAdper);
        mTabSelect.setupWithViewPager(mVpSelect);
        mTabSelect.getTabAt(0).setText("海淀区");
        mTabSelect.getTabAt(1).setText("日期");
        mTabSelect.getTabAt(2).setText("价格最低");
        mTabSelect.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position==1) {
                    Toast.makeText(SelectTheatersActivity.this, "挺好", Toast.LENGTH_SHORT).show();
                    mCPreantent.onDateListData();
                    initPopWindow(tab.view);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position==1) {
                    Toast.makeText(SelectTheatersActivity.this, "挺好", Toast.LENGTH_SHORT).show();
                    mCPreantent.onDateListData();
                    initPopWindow(tab.view);
                }
            }
        });
    }

    private void initView() {
        mTabSelect = (TabLayout) findViewById(R.id.tab_select);
        mVpSelect = (ViewPager) findViewById(R.id.vp_select);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof DateShow) {
            List<String> result = ((DateShow) o).getResult();
            DateAdper dateAdper = new DateAdper();
            dateAdper.addAll(result);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2, RecyclerView.VERTICAL, false);
            mRv_date.setLayoutManager(gridLayoutManager);
            mRv_date.setAdapter(dateAdper);
            dateAdper.setOnClickListener(new DateAdper.OnClickListener() {
                @Override
                public void onClick(String date) {
                    mTabSelect.getTabAt(1).setText(date);
                    EventBus.getDefault().postSticky(new DateEventShow(date));
                    mPopWindow.dismiss();
                }
            });

        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: "+mes);
    }



    private void initPopWindow(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_popip, null, false);
        mRv_date = view.findViewById(R.id.rv_date);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        mPopWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画
        mPopWindow.setTouchable(true);
        mPopWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mPopWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        mPopWindow.showAsDropDown(v, 50, 50);

        //设置popupWindow里的按钮的事件

    }
}
