package com.carpediem.randy.service;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.carpediem.randy.service.view.MusicAdapter;

import java.util.ArrayList;
import java.util.List;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtnPreSong;
    private Button mBtnNextSong;
    private Button mBtnPauseOrRestart;
    private TextView mTvCurrentSong;
    private RecyclerView mRvSongList;

    private MusicPlayerPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        mBtnNextSong = (Button)findViewById(R.id.music_activity_next_song);
        mBtnPauseOrRestart = (Button)findViewById(R.id.music_activity_pause);
        mBtnPreSong = (Button)findViewById(R.id.music_activity_pre_song);
        mTvCurrentSong = (TextView)findViewById(R.id.music_activity_current_song);
        mRvSongList = (RecyclerView)findViewById(R.id.music_activity_song_list);

        mBtnNextSong.setOnClickListener(this);
        mBtnPreSong.setOnClickListener(this);
        mBtnPauseOrRestart.setOnClickListener(this);

        mRvSongList.setHasFixedSize(true);
        mRvSongList.setLayoutManager(new LinearLayoutManager(this));

        //发送请求去查找当期歌单列表
        mPresenter = new MusicPlayerPresenter();

        MusicAdapter musicAdapter = new MusicAdapter(getMockedSongList());
        musicAdapter.setOnSongSelectedListener(mPresenter);

        mRvSongList.setAdapter(new MusicAdapter(getMockedSongList()));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.music_activity_next_song:
                break;
            case R.id.music_activity_pre_song:
                break;
            case R.id.music_activity_pause:
                break;
        }
    }
    private List<Music> getMockedSongList() {
        List<Music> mock = new ArrayList<>();
        Music music = new Music();
        mock.add(music);
        return mock;
    }

}
