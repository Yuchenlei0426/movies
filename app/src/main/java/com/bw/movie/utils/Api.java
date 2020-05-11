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
    String LOGIN_URL = "user/v2/login";
    //    注册
    String REGISTER_URL = "user/v2/register";
    //    验证码
    String SENDOUTEMAILCODE_URL = "user/v2/sendOutEmailCode";
    //    详情
    String MOVIESDETAIL_URL = "movie/v2/findMoviesDetail";
    //    电影评论
    String COMMENT_URL = "movie/v2/findAllMovieComment";
    //    推荐影院
    String RECOMMEND_URL = "cinema/v1/findRecommendCinemas";
    //    附近影院
    String NEARBY_URL = "cinema/v1/findNearbyCinemas";
    //    查询区域
    String REGION_URL = "tool/v2/findRegionList";
    //    根据区域查询影院
    String CINEMABYREGION_URL = "cinema/v2/findCinemaByRegion";
    //    电影评论
    String MOVIECOMMENT_URL = "movie/v1/verify/movieComment";
    //    查找日期列表
    String FINDDATELIST_URL = "tool/v2/findDateList";
    //    按日期查找电影院信息
    String CINEMASINFOBYDATE_URL = "movie/v2/findCinemasInfoByDate";

    //    查找电影院信息
    String FINDCINEMAINFO_URL = "cinema/v1/findCinemaInfo";

    //    根据价格查询影院
    String FINDCINEMASINFOBYPRICE_URL = "movie/v2/findCinemasInfoByPrice";
    //    根据电影ID 和影院ID 查询影厅
    String FINDMOVIESCHEDULE_URL = "movie/v2/findMovieSchedule";
    //    根据影厅ID 查询座位
    String FINDSEATINFO_URL = "movie/v2/findSeatInfo";
    //    微信登录
    String WECHATBINDINGLOGIN_URL = "user/v1/weChatBindingLogin";
    //    购票下单
    String BUYMOVIETICKETS_URL = "movie/v2/verify/buyMovieTickets";
    //支付
    String PAY_URL = "movieApi/movie/v2/verify/pay";


}
