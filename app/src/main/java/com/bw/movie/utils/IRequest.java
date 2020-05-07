package com.bw.movie.utils;

import com.bw.movie.bean.banner.HomeBannerShow;
import com.bw.movie.bean.cinema_bean.RecommendShow;
import com.bw.movie.bean.cinema_bean.findCinemaByRegion.CinemaByRegion;
import com.bw.movie.bean.cinema_bean.findregion.FindRegionShow;
import com.bw.movie.bean.cinema_bean.nearbycinemas.NearbyCinemas;
import com.bw.movie.bean.code_send.CodeSendShow;
import com.bw.movie.bean.comment_details.CommentShow;
import com.bw.movie.bean.comment_write.WriteCommentShow;
import com.bw.movie.bean.home_comingsoonmovie.ComingSoonShow;
import com.bw.movie.bean.home_hotmovie.HotShow;
import com.bw.movie.bean.home_release.ReleaseShow;
import com.bw.movie.bean.login_bean.LoginShow;
import com.bw.movie.bean.movie_detail.MovieDetailShow;
import com.bw.movie.bean.register.RegisterShow;

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
    Observable<WriteCommentShow> movieComment(@Header("userId")int userId,
                                              @Header("sessionId")String sessionId,
                                              @Field("movieId") int movieId,
                                              @Field("commentContent") String commentContent,
                                              @Field("score")float score);
}
