package com.example.hunter_j.hunter_circledemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.hunter_j.hunter_circledemo.MyApplication;
import com.example.hunter_j.hunter_circledemo.R;
import com.example.hunter_j.hunter_circledemo.adapter.viewholder.CircleViewHolder;
import com.example.hunter_j.hunter_circledemo.adapter.viewholder.ImageViewHolder;
import com.example.hunter_j.hunter_circledemo.adapter.viewholder.URLViewHolder;
import com.example.hunter_j.hunter_circledemo.bean.ActionItem;
import com.example.hunter_j.hunter_circledemo.bean.CircleItem;
import com.example.hunter_j.hunter_circledemo.bean.CommentConfig;
import com.example.hunter_j.hunter_circledemo.bean.CommentItem;
import com.example.hunter_j.hunter_circledemo.bean.FavortItem;
import com.example.hunter_j.hunter_circledemo.bean.PhotoInfo;
import com.example.hunter_j.hunter_circledemo.mvp.presenter.CirclePresenter;
import com.example.hunter_j.hunter_circledemo.utils.DatasUtil;
import com.example.hunter_j.hunter_circledemo.utils.GlideCircleTransform;
import com.example.hunter_j.hunter_circledemo.utils.UrlUtils;
import com.example.hunter_j.hunter_circledemo.widgets.CommentListView;
import com.example.hunter_j.hunter_circledemo.widgets.ExpandTextView;
import com.example.hunter_j.hunter_circledemo.widgets.MultiImageView;
import com.example.hunter_j.hunter_circledemo.widgets.PraiseListView;
import com.example.hunter_j.hunter_circledemo.widgets.SnsPopupWindow;
import com.example.hunter_j.hunter_circledemo.widgets.dialog.CommentDialog;

import java.util.List;

/**
 * Created by hunter_J on 2017/11/2.
 */

public class CircleAdapter extends BaseRecycleViewAdapter {

    public final static int TYPE_HEAD = 0;

    private static final int STATE_IDIE= 0;
    private static final int STATE_ACTIVED= 1;
    private static final int STATE_DEACTIVED= 2;
    private int videoState = STATE_IDIE;
    public static final int HEADVIEW_SIZE = 1;

    int curPlayIndex =-1;

    private CirclePresenter presenter;
    private Context context;

    public void setCirclePresenter(CirclePresenter presenter) {
        this.presenter = presenter;
    }

    public CircleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_HEAD;
        }
        int itemType = 0;
        CircleItem item = (CircleItem) datas.get(position-1);
        if (CircleItem.TYPE_URL.equals(item.getType())){
            itemType = CircleViewHolder.TYPE_URL;
        }else if (CircleItem.TYPE_IMG.equals(item.getType())){
            itemType = CircleViewHolder.TYPE_IMAGE;
        }else if (CircleItem.TYPE_VIDEO.equals(item.getType())){
            itemType = CircleViewHolder.TYPE_VIDEO;
        }
        return itemType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_HEAD){
            View headView = LayoutInflater.from(parent.getContext()).inflate(R.layout.head_circle,parent,false);
            viewHolder = new HeaderViewHolder(headView);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_circle_item,parent,false);

            if (viewType == CircleViewHolder.TYPE_URL){
                viewHolder = new URLViewHolder(view);
            }else if (viewType == CircleViewHolder.TYPE_IMAGE){
                viewHolder = new ImageViewHolder(view);
            }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEAD){
            //HeaderViewHolder holder = (HeaderViewHolder) viewHolder;
        }else {
            final int circlePosition = position - HEADVIEW_SIZE;
            CircleViewHolder holder = (CircleViewHolder) viewHolder;
            final CircleItem circleItem = (CircleItem) datas.get(circlePosition);
            final String circleId = circleItem.getId();
            String name = circleItem.getUser().getName();
            String headImg = circleItem.getUser().getHeadUrl();
            String content = circleItem.getContent();
            String creatTime = circleItem.getCreateTime();
            final List<FavortItem> favortDatas = circleItem.getFavorters();
            final List<CommentItem> commentDatas = circleItem.getComments();
            boolean hasFavort = circleItem.hasFavort();
            boolean hasComment = circleItem.hasComment();

            Glide.with(context).load(headImg).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.color.bg_no_photo).transform(new GlideCircleTransform(context)).into(holder.headIv);

            holder.nameTv.setText(name);
            holder.timeTv.setText(creatTime);

            if (!TextUtils.isEmpty(content)){
                holder.contentTv.setExpand(circleItem.isExpand());
                holder.contentTv.setExpandStatusListener(new ExpandTextView.ExpandStatusListener() {
                    @Override
                    public void statusChange(boolean isExpand) {
                        circleItem.setExpand(isExpand);
                    }
                });

                holder.contentTv.setText(UrlUtils.formatUrlString(content));
            }
            holder.contentTv.setVisibility(TextUtils.isEmpty(content) ? View.GONE : View.VISIBLE);

            if (DatasUtil.curUser.getId().equals(circleItem.getUser().getId())){
                holder.deleteBtn.setVisibility(View.VISIBLE);
            }else {
                holder.deleteBtn.setVisibility(View.GONE);
            }
            holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //删除
                    if (presenter != null){
                        presenter.deleteCircle(circleId);
                    }
                }
            });
            if (hasFavort || hasComment){
                if (hasFavort){ //处理顶赞列表
                    holder.praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            String userName = favortDatas.get(position).getUser().getName();
                            String userId = favortDatas.get(position).getUser().getId();
                            Toast.makeText(MyApplication.getContext(), userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
                        }
                    });
                    holder.praiseListView.setDatas(favortDatas);
                    holder.praiseListView.setVisibility(View.VISIBLE);
                }else {
                    holder.praiseListView.setVisibility(View.GONE);
                }

                if (hasComment){
                    holder.commentListView.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int commentPosition) {
                            CommentItem commentItem = commentDatas.get(commentPosition);
                            if (DatasUtil.curUser.getId().equals(commentItem.getUser().getId())){ //恢复或者删除自己的评论
                                CommentDialog dialog = new CommentDialog(context, presenter, commentItem, circlePosition);
                                dialog.show();
                            }else { //回复别人的评论
                                if (presenter != null){
                                    CommentConfig config = new CommentConfig();
                                    config.circlePosition = circlePosition;
                                    config.commentType = CommentConfig.Type.REPLY;
                                    config.commentPosition = commentPosition;
                                    config.replyUser = commentItem.getUser();
                                    presenter.showEditTextBody(config);
                                }
                            }
                        }
                    });
                    holder.commentListView.setOnItemLongClickListener(new CommentListView.OnItemLongClickListener() {
                        @Override
                        public void onItemLongClick(int position) {
                            //长按进行复制或者删除
                            CommentItem commentItem = commentDatas.get(position);
                            CommentDialog dialog = new CommentDialog(context, presenter, commentItem, circlePosition);
                            dialog.show();
                        }
                    });
                    holder.commentListView.setDatas(commentDatas);
                    holder.commentListView.setVisibility(View.VISIBLE);
                }else {
                    holder.commentListView.setVisibility(View.GONE);
                }
                holder.digCommentBody.setVisibility(View.VISIBLE);
            }else {
                holder.digCommentBody.setVisibility(View.GONE);
            }

            holder.digLine.setVisibility(hasFavort && hasComment ? View.VISIBLE : View.GONE);

            final SnsPopupWindow snsPopupWindow = holder.snsPopupWindow;
            //判断是否已经点赞
            String curUserFavortId = circleItem.getCurUserFavortId(DatasUtil.curUser.getId());
            if (!TextUtils.isEmpty(curUserFavortId)){
                snsPopupWindow.getmActionItems().get(0).mTitle = "取消";
            }else {
                snsPopupWindow.getmActionItems().get(0).mTitle = "赞";
            }
            snsPopupWindow.update();
            snsPopupWindow.setmItemClickListener(new PopupItemClickListener(curUserFavortId,circlePosition,circleItem));
            holder.snsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //弹出PopWindow
                    snsPopupWindow.showPopupWindow(view);
                }
            });

            holder.urlTipTv.setVisibility(View.GONE);
            switch (holder.viewType){
                case CircleViewHolder.TYPE_URL:// 处理链接动态的链接内容和和图片
                    if (holder instanceof URLViewHolder){
                        String linkImg = circleItem.getLinkImg();
                        String linkTitle = circleItem.getLinkTitle();
                        Glide.with(context).load(linkImg).into(((URLViewHolder) holder).urlImageIv);
                        ((URLViewHolder) holder).urlContentTv.setText(linkTitle);
                        ((URLViewHolder) holder).urlBody.setVisibility(View.VISIBLE);
                        holder.urlTipTv.setVisibility(View.VISIBLE);
                    }
                    break;
                case CircleViewHolder.TYPE_IMAGE:// 处理图片
                    if (holder instanceof ImageViewHolder){
                        List<PhotoInfo> photos = circleItem.getPhotos();
                        if (photos != null && photos.size() > 0){
                            ((ImageViewHolder) holder).multiImageView.setVisibility(View.VISIBLE);
                            ((ImageViewHolder) holder).multiImageView.setList(photos);
                            ((ImageViewHolder) holder).multiImageView.setmOnItemClickListener(new MultiImageView.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    //imagesize是作为loading时的图片size
                                    Toast.makeText(context,"点击了第："+position+"张",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            ((ImageViewHolder) holder).multiImageView.setVisibility(View.GONE);
                        }
                    }
                    break;

                case CircleViewHolder.TYPE_VIDEO:
                    // TODO ViedioViewHolder
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size()+1; //有head需要+1
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class PopupItemClickListener implements SnsPopupWindow.OnItemClickListener{

        private String mFavortId;
        //动态在列表中的位置
        private int mCirclePosition;
        private long mLastTime = 0;
        private CircleItem mCircleItem;

        public PopupItemClickListener(String mFavortId, int mCirclePosition, CircleItem mCircleItem) {
            this.mFavortId = mFavortId;
            this.mCirclePosition = mCirclePosition;
            this.mCircleItem = mCircleItem;
        }

        @Override
        public void onItemClick(ActionItem item, int position) {
            switch (position){
                case 0: //点赞、取消点赞
                    if (System.currentTimeMillis() - mLastTime < 700) //防止快速点击操作
                        return;
                    mLastTime = System.currentTimeMillis();
                    if (presenter != null){
                        if ("赞".equals(item.mTitle.toString())){
                            presenter.addFavort(mCirclePosition);
                        }else { //取消点赞
                            presenter.deleteFavort(mCirclePosition, mFavortId);
                        }
                    }
                    break;
                case 1: //发布评论
                    if (presenter != null){
                        CommentConfig config = new CommentConfig();
                        config.circlePosition = mCirclePosition;
                        config.commentType = CommentConfig.Type.PUBLIC;
                        presenter.showEditTextBody(config);
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
