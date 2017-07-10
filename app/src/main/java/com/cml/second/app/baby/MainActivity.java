package com.cml.second.app.baby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    UMShareAPI umShareAPI;

    @Override
    protected void onRestart() {
        Log.e("lifecycle", "onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.e("lifecycle", "onresume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e("lifecycle", "onPause");
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        umShareAPI = UMShareAPI.get(this);
        umShareAPI.doOauthVerify(this, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(getApplicationContext(), "onComplete", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "onError", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Toast.makeText(getApplicationContext(), "onCancel", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        umShareAPI.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, " MainActivityonActivityResult", Toast.LENGTH_LONG).show();
    }
}
