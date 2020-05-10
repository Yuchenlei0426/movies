package com.bw.movie.bean.cinemas_date;

import java.util.List;

public class CinemasDateShow {
    List<CinemasDateResult> result;
    String message;
    String status;

    public List<CinemasDateResult> getResult() {
        return result;
    }

    public void setResult(List<CinemasDateResult> result) {
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
