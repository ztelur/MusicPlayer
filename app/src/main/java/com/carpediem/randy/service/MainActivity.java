package com.carpediem.randy.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener{
    private EditText mEtUrl;
    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(this);
        mEtUrl = (EditText)findViewById(R.id.edittext);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            String url = mEtUrl.getText().toString();
            downloadFile(url);
        }
    }
    private void downloadFile(String url) {
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);
    }

}
