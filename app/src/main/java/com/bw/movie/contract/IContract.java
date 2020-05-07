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
//        详情
        void onMovieDetailData(Object...args);
//        电影评论
        void onCommentData(Object...args);

        //    推荐
        void onRecommendData(Object...args);
        //    附近
        void onNearbyData(Object...args);
        //    查询区域
        void onRegionData(Object...args);
        //    根据区域查询影院
        void onCinemabyregionData(Object...args);
//        写评论
        void onMovieCommentData(Object...args);
//        日期
        void onDateListData(Object...args);
//        根据电影id，时间 查询播放影院信息
        void onCinemasinfobydateData(Object...args);

    }
}
