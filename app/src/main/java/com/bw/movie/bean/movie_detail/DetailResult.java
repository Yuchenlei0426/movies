package com.bw.movie.bean.movie_detail;

import java.util.List;

public class DetailResult {
        int commentNum;
        String duration;
        String imageUrl;
        List<MovieActor> movieActor;
        List<MovieDirector> movieDirector;
        int movieId;
        String movieType;
        String name;
        String placeOrigin;
        List<String> posterList;
        long releaseTime;
        double score;
        List<ShortFilmList> shortFilmList;
        String summary;
        int  whetherFollow;

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<MovieActor> getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(List<MovieActor> movieActor) {
        this.movieActor = movieActor;
    }

    public List<MovieDirector> getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(List<MovieDirector> movieDirector) {
        this.movieDirector = movieDirector;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOrigin() {
        return placeOrigin;
    }

    public void setPlaceOrigin(String placeOrigin) {
        this.placeOrigin = placeOrigin;
    }

    public List<String> getPosterList() {
        return posterList;
    }

    public void setPosterList(List<String> posterList) {
        this.posterList = posterList;
    }



    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<ShortFilmList> getShortFilmList() {
        return shortFilmList;
    }

    public void setShortFilmList(List<ShortFilmList> shortFilmList) {
        this.shortFilmList = shortFilmList;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getWhetherFollow() {
        return whetherFollow;
    }

    public void setWhetherFollow(int whetherFollow) {
        this.whetherFollow = whetherFollow;
    }
}
