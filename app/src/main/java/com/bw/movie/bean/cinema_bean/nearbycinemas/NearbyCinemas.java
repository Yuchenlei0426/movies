package com.bw.movie.bean.cinema_bean.nearbycinemas;

import java.util.List;

/**
 *@describe(描述)：附近影院
 *@data（日期）: 2020/5/4
 *@time（时间）: 15:30
 *@author（作者）: 于晨雷
 **/
public class NearbyCinemas {
    private List<NearbyCinemasResult> result ;

    private String message;

    private String status;

    public List<NearbyCinemasResult> getResult() {
        return result;
    }

    public void setResult(List<NearbyCinemasResult> result) {
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
