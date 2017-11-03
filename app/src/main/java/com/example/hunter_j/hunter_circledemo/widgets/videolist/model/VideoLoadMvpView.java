package com.example.hunter_j.hunter_circledemo.widgets.videolist.model;

import android.media.MediaPlayer;

import com.example.hunter_j.hunter_circledemo.widgets.videolist.widget.TextureVideoView;

/**
 * Created by hunter_J on 2017/11/2.
 */

public interface VideoLoadMvpView {

    TextureVideoView getVideoView();

    void videoBeginning();

    void videoStopped();

    void videoPrepared(MediaPlayer player);

    void videoResourceReady(String videoPath);

}
