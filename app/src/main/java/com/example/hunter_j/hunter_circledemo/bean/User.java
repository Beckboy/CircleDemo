package com.example.hunter_j.hunter_circledemo.bean;

/**
 * @ClassName: User
 * @Description: TODO(这里用一句话描述这个类的作用)
 * Created by hunter_J on 2017/11/1.
 */

public class User {
    private String id;
    private String name;
    private String headUrl;

    public User(String id, String name, String headUrl) {
        this.id = id;
        this.name = name;
        this.headUrl = headUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", headUrl='" + headUrl + '\'' +
                '}';
    }
}
