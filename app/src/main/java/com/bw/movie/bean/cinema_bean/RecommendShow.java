package com.bw.movie.bean.cinema_bean;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class RecommendShow {
    List<RecommendResult> result;
    String message;
    String status;

    public List<RecommendResult> getResult() {
        return result;
    }

    public void setResult(List<RecommendResult> result) {
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
