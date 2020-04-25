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

//    banner
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

//    正在热映
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

//    即将上映
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

//    热门电影
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

//    登陆
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

//    注册
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

//    发送验证码
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
}
