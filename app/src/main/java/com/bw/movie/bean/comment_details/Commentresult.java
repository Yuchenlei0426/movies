package com.bw.movie.bean.comment_details;

import java.util.List;

public class Commentresult {
    String commentContent;
    String commentHeadPic;
    int commentId;
    long commentTime;
    int commentUserId;
    String commentUserName;
    int greatNum;
    int isGreat;
    List<String> replyHeadPic;
    int replyNum;
    double score;

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentHeadPic() {
        return commentHeadPic;
    }

    public void setCommentHeadPic(String commentHeadPic) {
        this.commentHeadPic = commentHeadPic;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }


    public int getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(int commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserName() {
        return commentUserName;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public int getGreatNum() {
        return greatNum;
    }

    public void setGreatNum(int greatNum) {
        this.greatNum = greatNum;
    }

    public int getIsGreat() {
        return isGreat;
    }

    public void setIsGreat(int isGreat) {
        this.isGreat = isGreat;
    }

    public List<String> getReplyHeadPic() {
        return replyHeadPic;
    }

    public void setReplyHeadPic(List<String> replyHeadPic) {
        this.replyHeadPic = replyHeadPic;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
