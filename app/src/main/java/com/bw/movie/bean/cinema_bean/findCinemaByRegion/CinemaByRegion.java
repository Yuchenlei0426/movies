package com.bw.movie.bean.cinema_bean.findCinemaByRegion;

import java.util.List;

public class CinemaByRegion {
    private List<CinemaByRegionResult> result ;

    private String message;

    private String status;

    public List<CinemaByRegionResult> getResult() {
        return result;
    }

    public void setResult(List<CinemaByRegionResult> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
