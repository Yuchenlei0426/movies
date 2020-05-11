package com.bw.movie.utils;

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
import com.bw.movie.bean.login_bean.LoginShow;
import com.bw.movie.bean.movie_detail.MovieDetailShow;
import com.bw.movie.bean.movieschedule.MovieScheduleShow;
import com.bw.movie.bean.order_show.OrderShow;
import com.bw.movie.bean.pay.PayShow;
import com.bw.movie.bean.register.RegisterShow;
import com.bw.movie.bean.seatInfo.SaetInfoShow;
import com.bw.movie.bean.wechat.WeChatShow;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRequest {
    //banner
    @GET(Api.BANNER_URL)
    Observable<HomeBannerShow> banner();

    //正在热映
    @GET(Api.RELEASE_URL)
    Observable<ReleaseShow> release(@Query("page") int page, @Query("count") int count);

    //    即将上映
    @GET(Api.COMINGSOON_URL)
    Observable<ComingSoonShow> comingsoon(@Query("page") int page, @Query("count") int count);

    //    热门电影
    @GET(Api.HOTMOVIE_URL)
    Observable<HotShow> hot(@Query("page") int page, @Query("count") int count);

    //    登陆
    @POST(Api.LOGIN_URL)
    @FormUrlEncoded
    Observable<LoginShow> login(@Field("email") String email, @Field("pwd") String pwd);

    //    注册
    @POST(Api.REGISTER_URL)
    @FormUrlEncoded
    Observable<RegisterShow> register(@Field("nickName") String nickName, @Field("pwd") String pwd, @Field("email") String email, @Field("code") String code);

    //    发送验证码
    @POST(Api.SENDOUTEMAILCODE_URL)
    @FormUrlEncoded
    Observable<CodeSendShow> sendCode(@Field("email") String email);

    //    详情
    @GET(Api.MOVIESDETAIL_URL)
    Observable<MovieDetailShow> MoviesDetail(@Query("movieId") int movieId);

    //    评论
    @GET(Api.COMMENT_URL)
    Observable<CommentShow> comment(@Header("userId") int userId
            , @Header("sessionId") String sessionId
            , @Query("movieId") int movieId
            , @Query("page") int page
            , @Query("count") int count);

    //    推荐
    @GET(Api.RECOMMEND_URL)
    Observable<RecommendShow> recommend(@Header("userId") int userId
            , @Header("sessionId") String sessionId
            , @Query("page") int page
            , @Query("count") int count);

    //附近
    @GET(Api.NEARBY_URL)
    Observable<NearbyCinemas> nearby(@Header("userId") int userId,
                                     @Header("sessionId") String sessionId,
                                     @Query("longitude") double longitude,
                                     @Query("latitude") double latitude,
                                     @Query("page") int page,
                                     @Query("count") int count);

    //查询区域
    @GET(Api.REGION_URL)
    Observable<FindRegionShow> region();

    //    根据区域查询影院
    @GET(Api.CINEMABYREGION_URL)
    Observable<CinemaByRegion> cinemabyregion(@Query("regionId") int regionId);


    //    电影评论
    @POST(Api.MOVIECOMMENT_URL)
    @FormUrlEncoded
    Observable<WriteCommentShow> movieComment(@Header("userId") int userId,
                                              @Header("sessionId") String sessionId,
                                              @Field("movieId") int movieId,
                                              @Field("commentContent") String commentContent,
                                              @Field("score") float score);

    //    日期
    @GET(Api.FINDDATELIST_URL)
    Observable<DateShow> dateList();

    //根据电影id，时间 查询播放影院信息
    @GET(Api.CINEMASINFOBYDATE_URL)
    Observable<CinemasDateShow> cinemasinfobydate(@Query("movieId") int movieId, @Query("date") String date, @Query("page") int page, @Query("count") int count);

    //    影院详情
    @GET(Api.FINDCINEMAINFO_URL)
    Observable<CinemaInfoDetailsShow> findCinemaInfo(@Header("userId") int userId
            , @Header("sessionId") String sessionId
            , @Query("cinemaId") Integer cinemaId);


    //    根据价格查询影院
    @GET(Api.FINDCINEMASINFOBYPRICE_URL)
    Observable<FindCinemasInfoByPrice> findcinemasinfobyPrice(@Query("movieId") int movieId,
                                                              @Query("page") int page,
                                                              @Query("count") int count);

    //    根据电影ID 和影院ID 查询影厅
    @GET(Api.FINDMOVIESCHEDULE_URL)
    Observable<MovieScheduleShow> findMovieSchedule(@Query("movieId") Integer movieId, @Query("cinemaId") Integer cinemaId);

    //    根据影厅ID 查询座位
    @GET(Api.FINDSEATINFO_URL)
    Observable<SaetInfoShow> findSeatInfo(@Query("hallId") Integer hallId);

    //    微信登录
    @POST(Api.WECHATBINDINGLOGIN_URL)
    @FormUrlEncoded
    Observable<WeChatShow> weChat(@Field("code") String code);

    //购票下单
    @POST(Api.BUYMOVIETICKETS_URL)
    @FormUrlEncoded
    Observable<OrderShow> buyMovieTickets(@Header("userId") int userId,
                                          @Header("sessionId") String sessionId,
                                          @Field("scheduleId") int scheduleId,
                                          @Field("seat") String seat,
                                          @Field("sign") String sign);
    //支付
    @FormUrlEncoded
    @POST(Api.PAY_URL)
    Observable<PayShow> pay(@Header("userId") int userId,
                            @Header("sessionId") String sessionId,
                            @Field("payType") int paytype,
                            @Field("orderId")String orderId);

}
