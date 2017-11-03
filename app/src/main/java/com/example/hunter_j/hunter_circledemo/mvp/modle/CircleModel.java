package com.example.hunter_j.hunter_circledemo.mvp.modle;

import android.os.AsyncTask;

import com.example.hunter_j.hunter_circledemo.listener.IDataRequestListener;

/**
 *
 * @ClassName: CircleModel
 * @Description: 因为逻辑简单，这里我就不写model的接口了
 * Created by hunter_J on 2017/11/1.
 */

public class CircleModel {

    public CircleModel(){}

    public void loadData(IDataRequestListener listener){requestServer(listener);}

    public void deleteCircle(IDataRequestListener listener){requestServer(listener);}

    public void addFavort(IDataRequestListener listener){requestServer(listener);}

    public void deleteFavort(IDataRequestListener listener){requestServer(listener);}

    public void addComment(IDataRequestListener listener){requestServer(listener);}

    public void deleteComment(IDataRequestListener listener){requestServer(listener);}


    /**
     * @Title: requestServer
     * @Description: 与后台交互，因为demo是本地数据，不作处理
     * @param listener 设定文件
     * @return void 返回类型
     * @throws
     */
    private void requestServer(final IDataRequestListener listener){
        new AsyncTask<Object, Integer, Object>() {
            @Override
            protected Object doInBackground(Object... params) {
                //和后台交互
                return null;
            }

            protected void onPostExecute(Object result){listener.loadSuccess(result);}
        }.execute();
    }

}
