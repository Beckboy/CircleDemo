package com.example.hunter_j.hunter_circledemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.hunter_j.hunter_circledemo.listener.RecyclerViewItemListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hunter_J on 2017/11/2.
 */

public abstract class BaseRecycleViewAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected RecyclerViewItemListener itemListener;
    protected List<T> datas = new ArrayList<T>();

    public List<T> getDatas() {
        if (datas== null)
            datas = new ArrayList<T>();
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public void setItemListener(RecyclerViewItemListener itemListener) {
        this.itemListener = itemListener;
    }

}
