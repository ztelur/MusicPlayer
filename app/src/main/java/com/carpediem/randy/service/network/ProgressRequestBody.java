package com.carpediem.randy.service.network;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by randy on 16-5-26.
 */
public class ProgressRequestBody extends RequestBody{
    private final RequestBody mRequestBody;
    private final ProgressRequestListener mResponseListener;
    private BufferedSink mBufferedSink;
    public ProgressRequestBody(RequestBody body,ProgressRequestListener listener) {
        mRequestBody = body;
        mResponseListener = listener;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }
    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }
    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (mBufferedSink == null) {
            mBufferedSink = Okio.buffer(sink(sink));
            mRequestBody.writeTo(mBufferedSink);
            mBufferedSink.flush();
        }
    }
    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            long bytesWritten = 0L;
            long contentLength = 0L;
            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source,byteCount);
                if (contentLength() == 0) {
                    contentLength = contentLength();
                }
                bytesWritten += byteCount;
                mResponseListener.onRequestProgress(bytesWritten,contentLength,bytesWritten == contentLength);
            }
        };
    }
}
