package com.carpediem.randy.service;

import com.carpediem.randy.service.view.MusicAdapter;

import java.util.List;

/**
 * Created by homer on 16-6-7.
 */
public class MusicPlayerPresenter implements MusicAdapter.OnSongSelectListener{
    private Music mCurrentMusic;
    public List<Music> getMusicList() {
        return null;
    }
    public void playMusic(Music music) {

    }
    public void pause() {

    }
    public void restart() {

    }
    public void playNextSong() {

    }
    public void playPreSong() {

    }

    @Override
    public void onSongSelected(Music music) {

    }
}
