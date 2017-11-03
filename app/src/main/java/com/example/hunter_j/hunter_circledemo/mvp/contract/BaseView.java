package com.example.hunter_j.hunter_circledemo.mvp.contract;

/**
 * Created by hunter_J on 2017/11/1.
 */

public interface BaseView {

    void showLoading(String msg);
    void hideLoading();
    void showError(String errorMsg);

}
