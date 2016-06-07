package com.carpediem.randy.service.network;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by randy on 16-5-26.
 */
public class ProgressResponseBody extends ResponseBody{
    private final ResponseBody responseBody;
    private final ProgressResponseListener progressResponseListener;
    private BufferedSource bufferedSource;


    public ProgressResponseBody(ResponseBody body, ProgressResponseListener listener) {
        this.responseBody = body;
        this.progressResponseListener = listener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        Log.e("TEST","BufferedSource source");
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }
    private Source source(Source source) {
        Log.e("TEST","source");
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink,byteCount);

                totalBytesRead += bytesRead != -1 ? bytesRead:0;

                progressResponseListener.onResponseProgress(totalBytesRead,contentLength(),bytesRead==-1);
                Log.e("TEST","read");
                return bytesRead;
            }
        };
    }

}
