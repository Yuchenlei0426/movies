package com.bw.movie.fragment;


import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.HomeAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.bean.banner.BannerResult;
import com.bw.movie.bean.banner.HomeBannerShow;
import com.bw.movie.bean.home_comingsoonmovie.ComingSoonResult;
import com.bw.movie.bean.home_comingsoonmovie.ComingSoonShow;
import com.bw.movie.bean.home_hotmovie.HotResult;
import com.bw.movie.bean.home_hotmovie.HotShow;
import com.bw.movie.bean.home_release.ReleaseResult;
import com.bw.movie.bean.home_release.ReleaseShow;
import com.bw.movie.preantent.CPreantent;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private XRecyclerView rvHome;
    private HomeAdper mHomeAdper;
    private ImageView ivPositioning;
    private TextView tvCity;

    @Override
    protected int onLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected BasePreantent initPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView(View view) {
        rvHome = (XRecyclerView) view.findViewById(R.id.rv_home);
        ivPositioning = (ImageView) view.findViewById(R.id.iv_Positioning);
        tvCity = (TextView) view.findViewById(R.id.tv_city);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvHome.setLayoutManager(linearLayoutManager);
        rvHome.setPullRefreshEnabled(true);
        rvHome.setLoadingMoreEnabled(true);
        rvHome.setRefreshProgressStyle(ProgressStyle.Pacman);
        rvHome.setLoadingMoreProgressStyle(ProgressStyle.Pacman);

        rvHome.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvHome.setRefreshProgressStyle(ProgressStyle.Pacman);
                        rvHome.refreshComplete();
                    }
                }, 2000);

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvHome.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
                        rvHome.loadMoreComplete();
                    }
                }, 2000);

            }
        });
    }

    @Override
    protected void onData() {
        mCPreantent.onBannerData();
        mCPreantent.onDreleaseData(1, 5);
        mCPreantent.onComingsoonData(1, 5);
        mCPreantent.onHotData(1, 5);
//        定位点击事件
        ivPositioning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onSuccess(Object o) {
//        banner
        if (o instanceof HomeBannerShow) {
            List<BannerResult> result = ((HomeBannerShow) o).getResult();
            mHomeAdper = new HomeAdper();
            mHomeAdper.onAddBannerAll(result);
            rvHome.setAdapter(mHomeAdper);
        }
//        正在热映
        if (o instanceof ReleaseShow) {
            List<ReleaseResult> result = ((ReleaseShow) o).getResult();
            if (mHomeAdper != null) {
                mHomeAdper.onAddReleaseAll(result);
                mHomeAdper.notifyDataSetChanged();
            }
        }
//        即将上映
        if (o instanceof ComingSoonShow) {
            List<ComingSoonResult> result = ((ComingSoonShow) o).getResult();
            if (mHomeAdper != null) {
                mHomeAdper.onAddComingSoonAll(result);
                mHomeAdper.notifyDataSetChanged();
            }
        }
//        热门电影
        if (o instanceof HotShow) {
            List<HotResult> result = ((HotShow) o).getResult();
            if (mHomeAdper != null) {
                mHomeAdper.onAddHotAll(result);
                mHomeAdper.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: " + mes);
    }

}
