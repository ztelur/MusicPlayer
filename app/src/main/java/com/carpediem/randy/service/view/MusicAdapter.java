package com.carpediem.randy.service.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.carpediem.randy.service.Music;
import com.carpediem.randy.service.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by homer on 16-6-7.
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> implements View.OnClickListener{
    private final List<Music> mDataSet = new ArrayList<>();
    private OnSongSelectListener mListener;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvSongName;
        public ViewHolder(View itemView) {
            super(itemView);
        }

    }

    public MusicAdapter(List<Music> list) {
        super();
        mDataSet.clear();
        mDataSet.addAll(list);
    }

    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.item_song_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MusicAdapter.ViewHolder holder, int position) {
        holder.mTvSongName.setText(getSongNameAt(position));
        holder.mTvSongName.setTag(position);
        holder.mTvSongName.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    private String getSongNameAt(int position) {
        return getSongAt(position).getName();
    }
    private Music getSongAt(int position) {
        if (position <0 || position >= getItemCount()) {
            throw new IndexOutOfBoundsException("postion <0 or > itemcout");
        }
        return mDataSet.get(position);
    }

    @Override
    public void onClick(View v) {
        onSongSelect(getSongAt((int)v.getTag()));
    }

    public void setOnSongSelectedListener(OnSongSelectListener listener) {
        mListener = listener;
    }
    public void onSongSelect(Music music) {
        if (mListener == null) {
            return;
        }
        mListener.onSongSelected(music);
    }
    public interface OnSongSelectListener {
        void onSongSelected(Music music);
    }
}
