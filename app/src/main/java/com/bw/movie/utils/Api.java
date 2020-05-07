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
//    推荐影院
    String RECOMMEND_URL ="cinema/v1/findRecommendCinemas";
//    附近影院
    String NEARBY_URL ="cinema/v1/findNearbyCinemas";
//    查询区域
    String REGION_URL ="tool/v2/findRegionList";
//    根据区域查询影院
    String CINEMABYREGION_URL ="cinema/v2/findCinemaByRegion";
    String MOVIECOMMENT_URL ="movie/v1/verify/movieComment";
    String FINDDATELIST_URL ="tool/v2/findDateList";
    String CINEMASINFOBYDATE_URL ="movie/v2/findCinemasInfoByDate";



}
