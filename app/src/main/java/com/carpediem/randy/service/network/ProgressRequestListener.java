package com.carpediem.randy.service.network;

/**
 * Created by randy on 16-5-26.
 */
public interface ProgressRequestListener {
    void onRequestProgress(long bytesWritten,long contentLength,boolean done);
}
