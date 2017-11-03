package com.example.hunter_j.hunter_circledemo.mvp.presenter;

import android.view.View;

import com.example.hunter_j.hunter_circledemo.bean.CircleItem;
import com.example.hunter_j.hunter_circledemo.bean.CommentConfig;
import com.example.hunter_j.hunter_circledemo.bean.CommentItem;
import com.example.hunter_j.hunter_circledemo.bean.FavortItem;
import com.example.hunter_j.hunter_circledemo.listener.IDataRequestListener;
import com.example.hunter_j.hunter_circledemo.mvp.contract.CircleContract;
import com.example.hunter_j.hunter_circledemo.mvp.modle.CircleModel;
import com.example.hunter_j.hunter_circledemo.utils.DatasUtil;

import java.util.List;

/**
 * @ClassName: CirclePresenter
 * @Description: 通知model请求服务器和通知view更新
 * Created by hunter_J on 2017/11/1.
 */

public class CirclePresenter implements CircleContract.Presenter {

    private CircleModel circleModel;
    private CircleContract.View view;

    public CirclePresenter(CircleContract.View view) {
        circleModel = new CircleModel();
        this.view = view;
    }

    @Override
    public void loadData(int loadType) {

        List<CircleItem> datas = DatasUtil.createCircleDatas();
        if (view!=null){
            view.update2loadData(loadType,datas);
        }

    }

    /**
     * @Title: deleteCircle
     * @Description: 删除动态
     * @param circleId
     * @return: void 返回类型
     * @throws
     */
    @Override
    public void deleteCircle(final String circleId) {
        circleModel.deleteCircle(new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {
                if (view!=null){
                    view.update2DeleteCircle(circleId);
                }
            }
        });
    }

    @Override
    public void addFavort(final int circlePosition) {
        circleModel.addFavort(new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {
                FavortItem item = DatasUtil.createCurUserFavortItem();
                if (view!=null){
                    view.update2AddFavorite(circlePosition,item);
                }
            }
        });
    }

    /**
     *
     * @Title: deleteFavort
     * @Description: 取消点赞
     * @param circlePosition
     * @param favortId
     * @return void    返回类型
     * @throws
     */
    @Override
    public void deleteFavort(final int circlePosition, final String favortId) {
        circleModel.deleteFavort(new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {
                if (view!=null){
                    view.update2DeleteFavort(circlePosition, favortId);
                }
            }
        });
    }

    /**
     * @Title: addComment
     * @Description: 增加评论
     * @param content
     * @param config CommentConfig
     * @return void 返回类型
     * @throws
     */
    public void addComment(final String content, final CommentConfig config){
        if (config == null) return;
        circleModel.addComment(new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {
                CommentItem newItem = null;
                if (config.commentType == CommentConfig.Type.PUBLIC){
                    newItem = DatasUtil.createPublicComment(content);
                }else if (config.commentType == CommentConfig.Type.REPLY){
                    newItem = DatasUtil.createReplyComment(config.replyUser,content);
                }
                if (view!=null){
                    view.upadte2AddComment(config.circlePosition, newItem);
                }
            }
        });
    }

    /**
     * @Title: deleteComment
     * @Description: 删除评论
     * @param circlePosition
     * @param commentId
     * @return void    返回类型
     * @throws
     */
    @Override
    public void deleteComment(final int circlePosition, final String commentId) {
        circleModel.deleteComment(new IDataRequestListener() {
            @Override
            public void loadSuccess(Object object) {
                if (view!=null){
                    view.update2DeleteComment(circlePosition, commentId);
                }
            }
        });
    }


    /**
     *
     * @param commentConfig
     */
    public void showEditTextBody(CommentConfig commentConfig){
        if (view != null){
            view.updateEditTextBodyVisible(View.VISIBLE, commentConfig);
        }
    }

    /**
     * 清除对外部对象的引用，防止内存泄露
     */
    public void recycle(){this.view = null;}

}
