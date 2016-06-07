package com.carpediem.randy.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.carpediem.randy.service.network.OkHttpHelper;
import com.carpediem.randy.service.network.ProgressResponseListener;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by randy on 16-5-25.
 */
public class DownloadService extends IntentService implements ProgressResponseListener{
    private final static String sURL = "http://www.9apps.com/jump/down/com.flashlightled/app/?page=detail";
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    public DownloadService() {
        this("DownloadService");
    }
    public DownloadService(String name) {
        super(name);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
//        try {
//            log("onHandleIntent");
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }

        int id = 0;
        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Apk download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_launcher);
        OkHttpClient client = OkHttpHelper.getOkHttpClient(this);
        Request request = new Request.Builder().url(sURL).build();

        try {
            Response response = client.newCall(request).execute();
            Log.e("TEST","after execute");
            Log.e("TEST",response.body().contentType()+" "+response.body().contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onCreate() {
        super.onCreate();
        log("onCreate");
        mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log("onDestroy");
    }

    @Override
    public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
        Log.e("TEST","progress"+bytesRead+" "+contentLength);
        if (!done) {
            mBuilder.setProgress((int) contentLength, (int) bytesRead, false);
            mNotificationManager.notify(1, mBuilder.build());
        } else {
            mBuilder.setContentText("Download success")
                    .setProgress(0,0,false);
            mNotificationManager.notify(1,mBuilder.build());
        }
    }

    private void log(String msg) {
        Log.e("DownloadService",msg);
    }

}
