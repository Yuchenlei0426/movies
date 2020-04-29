package com.bw.movie.utils;

public interface Api {
//    baseurl
    String BASE_URL = "http://mobile.bwstudent.com/movieApi/";
//    banner
    String BANNER_URL = "tool/v2/banner";
    //    正在热映
    String RELEASE_URL = "movie/v2/findReleaseMovieList";
    //    即将上映
    String COMINGSOON_URL = "movie/v2/findComingSoonMovieList";
    //热门电影
    String HOTMOVIE_URL = "movie/v2/findHotMovieList";
//    登陆
    String LOGIN_URL ="user/v2/login";
//    注册
    String REGISTER_URL ="user/v2/register";
//    验证码
    String SENDOUTEMAILCODE_URL ="user/v2/sendOutEmailCode";
    //    详情
    String MOVIESDETAIL_URL ="movie/v2/findMoviesDetail";
//    电影评论
    String COMMENT_URL ="movie/v2/findAllMovieComment";


}
