package com.bw.movie.bean.home_comingsoonmovie;

/**
 *@describe(描述)：即将上映Result
 *@data（日期）: 2020/4/19
 *@time（时间）: 10:58
 *@author（作者）: 于晨雷
 **/
public class ComingSoonResult {
    String imageUrl;
    int  movieId;
    String name;
    long releaseTime;
    int wantSeeNum;
    int whetherReserve;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getWantSeeNum() {
        return wantSeeNum;
    }

    public void setWantSeeNum(int wantSeeNum) {
        this.wantSeeNum = wantSeeNum;
    }

    public int getWhetherReserve() {
        return whetherReserve;
    }

    public void setWhetherReserve(int whetherReserve) {
        this.whetherReserve = whetherReserve;
    }
}
