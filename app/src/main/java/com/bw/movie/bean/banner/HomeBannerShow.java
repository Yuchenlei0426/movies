package com.bw.movie.bean.banner;

import java.util.List;

public class HomeBannerShow {
    List<BannerResult> result;
    String message;
    String status;

    public List<BannerResult> getResult() {
        return result;
    }

    public void setResult(List<BannerResult> result) {
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
