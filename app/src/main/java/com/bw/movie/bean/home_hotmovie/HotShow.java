package com.bw.movie.bean.home_hotmovie;

import java.util.List;
/**
 *@describe(描述)：热门电影
 *@data（日期）: 2020/4/19
 *@time（时间）: 11:03
 *@author（作者）: 于晨雷
 **/
public class HotShow {
    List<HotResult> result;
    String message;
    String status;

    public List<HotResult> getResult() {
        return result;
    }

    public void setResult(List<HotResult> result) {
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
