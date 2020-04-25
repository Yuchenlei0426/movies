package com.bw.movie.bean.home_release;
/**
 *@describe(描述)：正在热映
 *@data（日期）: 2020/4/18
 *@time（时间）: 19:42
 *@author（作者）: 于晨雷
 **/
public class ReleaseResult {
    String director;
    String imageUrl;
    int movieId;
    String name;
    double score;
    String starring;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }
}
