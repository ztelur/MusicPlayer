package com.carpediem.randy.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.carpediem.randy.service.music.MusicPlayer;

/**
 * Created by homer on 16-6-6.
 */
public class MusicService extends Service implements MusicPlayer{
    private final IBinder mBinder = new LocalMusicBinder();
    private MediaPlayer mMusicPlayer;
    public MusicService() {
        super();
    }

    public class LocalMusicBinder extends Binder {
        MusicPlayer getMusicPlayer() {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMusicPlayer = new MediaPlayer();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 回收掉MediaPlayer
        mMusicPlayer.release();
        mMusicPlayer = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public void playSong() {
        initPlayerIfNeeded();
    }

    @Override
    public void pause() {

    }

    @Override
    public void restart() {

    }

    private void initPlayerIfNeeded() {
        if (mMusicPlayer == null) {
            mMusicPlayer = new MediaPlayer();
        }
    }


//  当内存比较少的时候可以去释放players
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}

