package com.example.hunter_j.hunter_circledemo.adapter.viewholder;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hunter_j.hunter_circledemo.R;

/**
 * Created by hunter_J on 2017/11/2.
 */

public class URLViewHolder extends CircleViewHolder {

    public LinearLayout urlBody;
    /** 链接的图片 **/
    public ImageView urlImageIv;
    /** 链接的标题 **/
    public TextView urlContentTv;

    public URLViewHolder(View itemView) {
        super(itemView, TYPE_URL);
    }

    @Override
    public void initSubView(int viewType, ViewStub viewStub) {
        if (viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }

        viewStub.setLayoutResource(R.layout.viewstub_urlbody);
        View subView = viewStub.inflate();
        LinearLayout urlBodyView = (LinearLayout) subView.findViewById(R.id.urlBody);
        if (urlBodyView != null){
            urlBody = urlBodyView;
            urlImageIv = (ImageView) subView.findViewById(R.id.urlImageIv);
            urlContentTv = (TextView) subView.findViewById(R.id.urlContentTv);
        }
    }


}
