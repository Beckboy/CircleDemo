package com.example.hunter_j.hunter_circledemo.mvp.contract;

import com.example.hunter_j.hunter_circledemo.bean.CircleItem;
import com.example.hunter_j.hunter_circledemo.bean.CommentConfig;
import com.example.hunter_j.hunter_circledemo.bean.CommentItem;
import com.example.hunter_j.hunter_circledemo.bean.FavortItem;

import java.util.List;

/**
 * Created by hunter_J on 2017/11/1.
 */

public interface CircleContract {

    interface View extends BaseView{
        void update2DeleteCircle(String circleId);
        void update2AddFavorite(int circlePosition, FavortItem addItem);
        void update2DeleteFavort(int circlePosition, String favortId);
        void upadte2AddComment(int circlePosition, CommentItem addItem);
        void update2DeleteComment(int circlePosition, String commentId);
        void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig);
        void update2loadData(int loadType, List<CircleItem> datas);
    }

    interface Presenter extends BasePresenter{
        void loadData(int loadType);
        void deleteCircle(String circleId);
        void addFavort(int circlePosition);
        void deleteFavort(int circlePosition, String favortId);
        void deleteComment(int circlePosition, String commentId);
    }

}
