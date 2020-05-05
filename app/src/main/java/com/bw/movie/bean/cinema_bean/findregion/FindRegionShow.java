package com.bw.movie.bean.cinema_bean.findregion;

import java.util.List;

public class FindRegionShow {
    private List<FindRegionResult> result ;

    private String message;

    private String status;

    public List<FindRegionResult> getResult() {
        return result;
    }

    public void setResult(List<FindRegionResult> result) {
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
