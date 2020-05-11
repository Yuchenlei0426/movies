package com.bw.movie.module;

import android.content.SharedPreferences;

import com.bw.movie.bean.banner.HomeBannerShow;
import com.bw.movie.bean.cinema_bean.RecommendShow;
import com.bw.movie.bean.cinema_bean.findCinemaByRegion.CinemaByRegion;
import com.bw.movie.bean.cinema_bean.findregion.FindRegionShow;
import com.bw.movie.bean.cinema_bean.nearbycinemas.NearbyCinemas;
import com.bw.movie.bean.cinemas_date.CinemasDateShow;
import com.bw.movie.bean.code_send.CodeSendShow;
import com.bw.movie.bean.comment_details.CommentShow;
import com.bw.movie.bean.comment_write.WriteCommentShow;
import com.bw.movie.bean.date.DateShow;
import com.bw.movie.bean.findHotMovieList.CinemaInfoDetailsShow;
import com.bw.movie.bean.findcinemasInfobyprice.FindCinemasInfoByPrice;
import com.bw.movie.bean.home_comingsoonmovie.ComingSoonShow;
import com.bw.movie.bean.home_hotmovie.HotShow;
import com.bw.movie.bean.home_release.ReleaseShow;
import com.bw.movie.bean.login_bean.LoginResult;
import com.bw.movie.bean.login_bean.LoginShow;
import com.bw.movie.bean.login_bean.UserInfo;
import com.bw.movie.bean.movie_detail.MovieDetailShow;
import com.bw.movie.bean.movieschedule.MovieScheduleShow;
import com.bw.movie.bean.order_show.OrderShow;
import com.bw.movie.bean.pay.PayShow;
import com.bw.movie.bean.register.RegisterShow;
import com.bw.movie.bean.seatInfo.SaetInfoShow;
import com.bw.movie.bean.wechat.WeChatResult;
import com.bw.movie.bean.wechat.WeChatShow;
import com.bw.movie.bean.wechat.WeChatUserInfo;
import com.bw.movie.contract.IContract;
import com.bw.movie.utils.App;
import com.bw.movie.utils.IRequest;
import com.bw.movie.utils.WorkUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.bw.movie.utils.App.mLogin;
/**
 * @describe(描述)：这是Moudle层
 * @data（日期）: 2020/4/24
 * @time（时间）: 14:33
 * @author（作者）: 于晨雷
 **/
public class CModule {

    //    banner
    public void onBannerData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.banner().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HomeBannerShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    //                    成功
                    @Override
                    public void onNext(HomeBannerShow homeBannerShow) {
                        String status = homeBannerShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(homeBannerShow);

                        }
                    }

                    //       失败
                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //    正在热映
    public void onReleaseData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.release((int) args[0], (int) args[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReleaseShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReleaseShow releaseShow) {
                        String status = releaseShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(releaseShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //    即将上映
    public void onComingsoonData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.comingsoon((int) args[0], (int) args[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ComingSoonShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ComingSoonShow comingSoonShow) {
                        String status = comingSoonShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(comingSoonShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //热门电影
    public void onHotData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.hot((int) args[0], (int) args[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotShow hotShow) {
                        String status = hotShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(hotShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //登陆
    public void onLoginData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.login((String) args[0], (String) args[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginShow loginShow) {
                        String status = loginShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(loginShow);
                            SharedPreferences mLogin = App.mLogin;
                            SharedPreferences.Editor edit = mLogin.edit();
                            LoginResult result = loginShow.getResult();
                            String sessionId = result.getSessionId();
                            int userId = result.getUserId();
                            UserInfo userInfo = result.getUserInfo();
                            String nickName = userInfo.getNickName();
                            String headPic = userInfo.getHeadPic();
                            edit.putString("sessionId", sessionId);
                            edit.putInt("userId", userId);
                            edit.putString("headPic", headPic);
                            edit.putString("nickName", nickName);
                            edit.commit();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //    注册
    public void onRegisterData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.register((String) args[0], (String) args[1], (String) args[2], (String) args[3])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegisterShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegisterShow registerShow) {
                        String status = registerShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(registerShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //     获取验证码
    public void onCodeSendData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.sendCode((String) args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CodeSendShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CodeSendShow codeSendShow) {
                        String status = codeSendShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(codeSendShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    //    详情
    public void onMovieDetailData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.MoviesDetail((int) args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetailShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetailShow movieDetailShow) {
                        String status = movieDetailShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(movieDetailShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//电影评论
    public void onCommentData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.comment((int) args[0], (String) args[1], (int) args[2], (int) args[3], (int) args[4])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentShow commentShow) {
                        String status = commentShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(commentShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    推荐
    public void onRecommendData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.recommend((int)args[0],(String)args[1],(int)args[2],(int)args[3])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendShow recommendShow) {
                        String status = recommendShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(recommendShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
//    附近
    public void onNearbyData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.nearby((int)args[0],(String)args[1],(double)args[2],(double)args[3],(int)args[4],(int)args[5])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NearbyCinemas>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NearbyCinemas nearbyCinemas) {
                        String status = nearbyCinemas.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(nearbyCinemas);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
//    查询区域
    public void onRegionData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.region()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindRegionShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindRegionShow findRegionShow) {
                        String status = findRegionShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(findRegionShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
//    根据区域查询影院
    public void onCinemabyregionData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.cinemabyregion((int)args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaByRegion>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaByRegion cinemaByRegion) {
                        String status = cinemaByRegion.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(cinemaByRegion);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 写评论
     * @param iModule
     * @param args
     */
    public void onMovieCommentData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.movieComment((int)args[0],(String)args[1],(int)args[2],(String)args[3],(float)args[4])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WriteCommentShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WriteCommentShow writeCommentShow) {
                        String status = writeCommentShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(writeCommentShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
   /**
     * 日期
     * @param iModule
     * @param args
     */
    public void onDateListData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.dateList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DateShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DateShow dateShow) {
                        String status = dateShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(dateShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     *根据电影id，时间 查询播放影院信息
     * @param iModule
     * @param args
     */
    public void onCinemasinfobydateData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.cinemasinfobydate((int)args[0],(String) args[1],(int)args[2],(int)args[3])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemasDateShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemasDateShow cinemasDateShow) {
                        String status = cinemasDateShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(cinemasDateShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 电影院信息
     * @param iModule
     * @param args
     */
    public void onCinemaInfoData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.findCinemaInfo((int)args[0],(String)args[1],(int)args[2])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CinemaInfoDetailsShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CinemaInfoDetailsShow homeShowCinemaInfo) {
                        String status = homeShowCinemaInfo.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(homeShowCinemaInfo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 根据价格查询影院
     * @param iModule
     * @param args
     */
   public void onFindcinemasinfobyPriceData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.findcinemasinfobyPrice((int)args[0],(int)args[1],(int)args[2])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindCinemasInfoByPrice>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindCinemasInfoByPrice findCinemasInfoByPrice) {
                        String status = findCinemasInfoByPrice.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(findCinemasInfoByPrice);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**
     * 根据电影ID 和影院ID 查询影厅
     * @param iModule
     * @param args
     */
   public void onFindMovieScheduleData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.findMovieSchedule((int)args[0],(int)args[1])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieScheduleShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieScheduleShow movieScheduleShow) {
                        String status = movieScheduleShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(movieScheduleShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**
     * 根据影厅ID 查询座位
     * @param iModule
     * @param args
     */
   public void onFindSeatInfoData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.findSeatInfo((int)args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SaetInfoShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SaetInfoShow saetInfoShow) {
                        String status = saetInfoShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(saetInfoShow);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**
     * 根据影厅ID  微信登录
     * @param iModule
     * @param args
     */
   public void onweChatData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.weChat((String) args[0])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeChatShow weChatShow) {
                        String status = weChatShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(weChatShow);
                            SharedPreferences mLogin = App.mLogin;
                            SharedPreferences.Editor edit = mLogin.edit();
                            WeChatResult result = weChatShow.getResult();
                            String sessionId = result.getSessionId();
                            int userId = result.getUserId();
                            WeChatUserInfo userInfo = result.getUserInfo();
                            String nickName = userInfo.getNickName();
                            String headPic = userInfo.getHeadPic();
                            edit.putString("sessionId", sessionId);
                            edit.putInt("userId", userId);
                            edit.putString("headPic", headPic);
                            edit.putString("nickName", nickName);
                            edit.commit();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
  /**
     * 根据影厅ID  支付
     * @param iModule
     * @param args
     */
   public void onweChatPayData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.pay((int) args[0],(String)args[1],(int)args[2],(String)args[3])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PayShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PayShow payShow) {
                        String status = payShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(payShow);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
  /**
     * 根据影厅ID  购票下单
     * @param iModule
     * @param args
     */
   public void onbuyMovieTicketsData(IContract.IModule iModule, Object... args) {
        IRequest iRequest = WorkUtil.getIncract().create(IRequest.class);
        iRequest.buyMovieTickets((int) args[0],(String)args[1],(int)args[2],(String)args[3],(String)args[4])
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<OrderShow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(OrderShow orderShow) {
                        String status = orderShow.getStatus();
                        if (status.equals("0000")) {
                            iModule.onSuccess(orderShow);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        iModule.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}