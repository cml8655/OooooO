package com.cml.second.app.baby.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.cml.second.app.baby.R;
import com.socks.library.KLog;

import java.io.File;

import butterknife.OnClick;

/**
 * Created by cmlBeliever on 2016/8/1.
 * 录音和播放声音操作
 */
public class AudioFragment extends BaseFragment {

    private static final String TAG = AudioFragment.class.getSimpleName();

    private File recorderFile;
    private MediaRecorder mMediaRecorder;
    private boolean isRecording;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File path = new File(Environment.getExternalStorageDirectory(), "audio");
        if (!path.exists()) {
            path.mkdirs();
        }
        recorderFile = new File(path, "recorder.3gp");

        mMediaRecorder = new MediaRecorder();
        try {
            //设置成3gp输出方式
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);// 音频源
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mMediaRecorder.setOutputFile(recorderFile.getAbsolutePath());
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// 音频格式
            mMediaRecorder.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("recorderFile", recorderFile.getAbsolutePath());
        super.onSaveInstanceState(outState);

        KLog.i(TAG, "onSaveInstanceState");
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (null != savedInstanceState && savedInstanceState.getString("recorderFile") != null) {
            recorderFile = new File(savedInstanceState.getString("recorderFile"));
        }
        KLog.i(TAG, "onViewStateRestored");
    }

    @OnClick({R.id.startAudio, R.id.play})
    public void onCick(View view) {
        switch (view.getId()) {
            case R.id.startAudio:
                startRecorder();
                break;
            case R.id.play:
                startPlay();
                break;
        }
    }

    MediaPlayer player;


    private void startPlay() {
        if (isRecording) {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
        if (null != player) {
            player.release();
        }
        player = new MediaPlayer();
        try {
            player.setDataSource(getActivity(), Uri.fromFile(recorderFile));
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setLooping(true);
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        if (null != player) {
            player.stop();
            player.release();
        }
        if (null != mMediaRecorder) {
            mMediaRecorder.release();
        }
        super.onDestroy();
    }

    private void startRecorder() {
        if (isRecording) {
            Toast.makeText(getActivity(), "正在录音", Toast.LENGTH_LONG).show();
            return;
        }
        isRecording = true;
        mMediaRecorder.start();
    }

    @Override
    protected int getContainerRes() {
        return R.layout.fragment_audio;
    }
}
