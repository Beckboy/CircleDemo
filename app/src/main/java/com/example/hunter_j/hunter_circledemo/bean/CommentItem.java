package com.example.hunter_j.hunter_circledemo.bean;

import java.io.Serializable;

/**
 * @ClassName: CommentItem
 * @Description: TODO(这里用一句话描述这个类的作用)
 * Created by hunter_J on 2017/11/1.
 */

public class CommentItem implements Serializable {

    private String id;
    private User user;
    private User toReplyUser;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getToReplyUser() {
        return toReplyUser;
    }

    public void setToReplyUser(User toReplyUser) {
        this.toReplyUser = toReplyUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
