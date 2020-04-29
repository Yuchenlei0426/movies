package com.bw.movie.fragment.details_fragment;


import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.detalis_adper.ActorAdper;
import com.bw.movie.adper.detalis_adper.DirectorAdper;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.bean.evenbean.MovieEvenShow;
import com.bw.movie.bean.movie_detail.DetailResult;
import com.bw.movie.bean.movie_detail.MovieActor;
import com.bw.movie.bean.movie_detail.MovieDetailShow;
import com.bw.movie.bean.movie_detail.MovieDirector;
import com.bw.movie.preantent.CPreantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @describe(描述)：介绍
 * @data（日期）: 2020/4/25
 * @time（时间）: 16:30
 * @author（作者）: 于晨雷
 **/
public class IntroduceFragment extends BaseNetFragment {

    private static final String TAG = "IntroduceFragment";
    private RecyclerView mRvMovieDirector;
    private RecyclerView mRvMovieActor;
    private int mMovieId;
    private TextView mTvSummary;
    private TextView mTvSummaryIntro;
    private TextView mTvIntroMovieDirector;
    private TextView mTvIntroMovieActor;

    @Override
    protected int onLayout() {
        return R.layout.fragment_introduce;
    }

    @Override
    protected BasePreantent initPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView(View view) {
        EventBus.getDefault().register(this);
        initView(view);
    }

    @Subscribe(sticky = true)
    public void onShow(MovieEvenShow movieEvenShow) {
        mMovieId = movieEvenShow.getMovieId();
    }

    @Override
    protected void onData() {
        mCPreantent.onMovieDetailData(mMovieId);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof MovieDetailShow) {
            String summary = ((MovieDetailShow) o).getResult().getSummary();
            mTvSummary.setText(summary);
            DetailResult result = ((MovieDetailShow) o).getResult();
            List<MovieActor> movieActor = result.getMovieActor();
            List<MovieDirector> movieDirector = result.getMovieDirector();
            mTvIntroMovieDirector.setText("导演"+"("+movieDirector.size()+")");
            mTvIntroMovieActor.setText("演员"+"("+movieActor.size()+")");
            DirectorAdper directorAdper = new DirectorAdper();
            directorAdper.addAll(movieDirector);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
            mRvMovieDirector.setLayoutManager(linearLayoutManager);
            mRvMovieDirector.setAdapter(directorAdper);
            ActorAdper actorAdper = new ActorAdper();
            actorAdper.addAll(movieActor);
            LinearLayoutManager linearLayoutManagerActor = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
            mRvMovieActor.setLayoutManager(linearLayoutManagerActor);
            mRvMovieActor.setAdapter(actorAdper);


        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: " + mes);
    }

    private void initView(View view) {
        mRvMovieDirector = (RecyclerView) view.findViewById(R.id.rv_movieDirector);
        mRvMovieActor = (RecyclerView) view.findViewById(R.id.rv_movieActor);
        mTvSummary = (TextView) view.findViewById(R.id.tv_summary);
        mTvSummaryIntro = (TextView) view.findViewById(R.id.tv_summary_intro);
        mTvIntroMovieDirector = (TextView) view.findViewById(R.id.tv_intro_movieDirector);
        mTvIntroMovieActor = (TextView)view.findViewById(R.id.tv_intro_movieActor);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
