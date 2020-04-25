package com.bw.movie.bean.home_release;

import java.util.List;
/**
 *@describe(描述)：正在热映
 *@data（日期）: 2020/4/18
 *@time（时间）: 19:41
 *@author（作者）: 于晨雷
 **/
public class ReleaseShow {
     List<ReleaseResult> result;
     String message;
     String status;

    public List<ReleaseResult> getResult() {
        return result;
    }

    public void setResult(List<ReleaseResult> result) {
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
