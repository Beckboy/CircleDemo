package com.example.hunter_j.hunter_circledemo.bean;

/**
 * Created by hunter_J on 2017/11/1.
 */

public class CommentConfig {
    public static enum Type{
        PUBLIC("public"),REPLY("reply");

        private String value;
        private Type(String value){this.value = value;}
    }

    public int circlePosition;
    public int commentPosition;
    public Type commentType;
    public User replyUser;

    @Override
    public String toString() {
        String replyUserStr = "";
        if (replyUser != null){
            replyUserStr = replyUser.toString();
        }
        return "CommentConfig{" +
                "circlePosition=" + circlePosition +
                ", commentPosition=" + commentPosition +
                ", commentType=" + commentType +
                ", replyUser=" + replyUserStr +
                '}';
    }
}
