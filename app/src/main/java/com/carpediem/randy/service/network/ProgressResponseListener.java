package com.carpediem.randy.service.network;

/**
 * Created by randy on 16-5-26.
 */
public interface ProgressResponseListener {
    void onResponseProgress(long bytesRead,long contentLength,boolean done);
}
