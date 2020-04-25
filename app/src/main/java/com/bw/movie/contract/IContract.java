package com.bw.movie.contract;

public interface IContract {
    interface IView{
        void onSuccess(Object o);
        void onFail(String mes);
    }
    interface IModule{
        void onSuccess(Object o);
        void onFail(String mes);
    }
    interface IPreantent{
//        banner
        void onBannerData(Object...args);
        //    正在热映
        void onDreleaseData(Object...args);
//        即将上映
        void onComingsoonData(Object...args);
//        热门电影
        void onHotData(Object...args);
//        登陆
        void onLoginData(Object...args);
//        注册
        void onRegisterData(Object...args);
//        发送验证码
        void onCodeSendData(Object...args);
    }
}
