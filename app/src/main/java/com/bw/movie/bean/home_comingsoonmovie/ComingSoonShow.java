package com.bw.movie.bean.home_comingsoonmovie;

import java.util.List;
/**
 *@describe(描述)：即将上映
 *@data（日期）: 2020/4/19
 *@time（时间）: 10:58
 *@author（作者）: 于晨雷
 **/
public class ComingSoonShow {
    List<ComingSoonResult> result;
    String message;
    String status;

    public List<ComingSoonResult> getResult() {
        return result;
    }

    public void setResult(List<ComingSoonResult> result) {
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
