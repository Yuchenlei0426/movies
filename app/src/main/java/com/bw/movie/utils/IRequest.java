package com.bw.movie.utils;

import com.bw.movie.bean.banner.HomeBannerShow;
import com.bw.movie.bean.code_send.CodeSendShow;
import com.bw.movie.bean.home_comingsoonmovie.ComingSoonShow;
import com.bw.movie.bean.home_hotmovie.HotShow;
import com.bw.movie.bean.home_release.ReleaseShow;
import com.bw.movie.bean.login_bean.LoginShow;
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
    Observable<RegisterShow> register(@Field("nickName")String nickName, @Field("pwd") String pwd, @Field("email")String email,@Field("code")String code);
//    发送验证码
    @POST(Api.SENDOUTEMAILCODE_URL)
    @FormUrlEncoded
    Observable<CodeSendShow> sendCode(@Field("email")String email);

}
