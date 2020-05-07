package com.bw.movie.preantent;

import com.bw.movie.base.BasePreantent;
import com.bw.movie.contract.IContract;
import com.bw.movie.module.CModule;

public class CPreantent extends BasePreantent implements IContract.IPreantent {

    private CModule mCModule;

    @Override
    protected void initModule() {
        mCModule = new CModule();
    }


    /**
     * banner
     * @param args 动态参数
     */
    @Override
    public void onBannerData(Object... args) {
        mCModule.onBannerData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }


    /**
     * 正在热映
     * @param args 动态参数
     */
    @Override
    public void onDreleaseData(Object... args) {
        mCModule.onReleaseData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }


    /**
     * 即将上映
     * @param args 动态参数
     */
    @Override
    public void onComingsoonData(Object... args) {
        mCModule.onComingsoonData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }


    /**
     * 热门电影
     * @param args 动态参数
     */
    @Override
    public void onHotData(Object... args) {
        mCModule.onHotData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }


    /**
     * 登陆
     * @param args 动态参数
     */
    @Override
    public void onLoginData(Object... args) {
        mCModule.onLoginData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }


    /**
     * 注册
     * @param args 动态参数
     */
    @Override
    public void onRegisterData(Object... args) {
        mCModule.onRegisterData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }


    /**
     * 发送验证码
     * @param args 动态参数
     */
    @Override
    public void onCodeSendData(Object... args) {
        mCModule.onCodeSendData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }

    /**
     * 电影详情
     * @param args 动态参数
     */
    @Override
    public void onMovieDetailData(Object... args) {
        mCModule.onMovieDetailData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);

    }
//电影评论
    @Override
    public void onCommentData(Object... args) {
        mCModule.onCommentData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }

    /**
     * 推荐
     * @param args 动态参数
     */
    @Override
    public void onRecommendData(Object... args) {
        mCModule. onRecommendData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }

    /**
     * 附近
     * @param args 动态参数
     */
    @Override
    public void onNearbyData(Object... args) {
        mCModule.onNearbyData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);

    }

    /**
     * 查询区域
     * @param args 动态参数
     */
    @Override
    public void onRegionData(Object... args) {
        mCModule.onRegionData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }

    /**
     * 根据区域查询影院
     * @param args 动态参数
     */
    @Override
    public void onCinemabyregionData(Object... args) {
        mCModule.onCinemabyregionData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }

    @Override
    public void onMovieCommentData(Object... args) {
        mCModule.onMovieCommentData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }


    /**
     * 根据电影id，时间 查询播放影院信息
     * @param args
     */
    @Override
    public void onDateListData(Object... args) {
        mCModule.onDateListData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        });
    }

    /**
     * 根据电影id，时间 查询播放影院信息
     * @param args
     */
    @Override
    public void onCinemasinfobydateData(Object... args) {
        mCModule.onCinemasinfobydateData(new IContract.IModule() {
            @Override
            public void onSuccess(Object o) {
                getView().onSuccess(o);
            }

            @Override
            public void onFail(String mes) {
                getView().onFail(mes);
            }
        },args);
    }
}
