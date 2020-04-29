package com.bw.movie.bean.comment_details;

import java.util.List;

/**
 *@describe(描述)：电影评论bean类
 *@data（日期）: 2020/4/29
 *@time（时间）: 14:21
 *@author（作者）: 于晨雷
 **/
public class CommentShow {
    List<Commentresult> result;
    String message;
    String status;

    public List<Commentresult> getResult() {
        return result;
    }

    public void setResult(List<Commentresult> result) {
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
