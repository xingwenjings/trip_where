package com.example.dashixun_zuoye.fragments;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dashixun_zuoye.DownLoadSeriver;
import com.example.dashixun_zuoye.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment implements View.OnClickListener {


    private ProgressBar upload;
    private TextView progress_tv;
    private Button bt_tv;
    private String getLoad="http://cdn.banmi.com/banmiapp/apk/banmi_330.apk";
    private String TAG="UploadFragment";
    private DownLoadSeriver.XiaZai binder;

    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_upload, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        upload = (ProgressBar) inflate.findViewById(R.id.upload);
        progress_tv = (TextView) inflate.findViewById(R.id.progress_tv);
        bt_tv = (Button) inflate.findViewById(R.id.bt_tv);

        bt_tv.setOnClickListener(this);
    }
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                int i = (int) msg.obj;
                if (i < 100) {
                    upload.setProgress(i + 1);
                    Log.e(TAG, "handleMessage: " + i);
                    progress_tv.setText("当前进度:" + (i + 1) + "%");
                } else {
                    Toast.makeText(getActivity(), "下载完成", Toast.LENGTH_SHORT).show();
                }

            }
        }
    };

    private ServiceConnection connection = new ServiceConnection() {
        //绑定服务调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (DownLoadSeriver.XiaZai) service;
            //binder.getData();
            binder.getDem();
        }

        //解绑服务调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_tv:
                initLoad();
                break;
        }
    }

    private void initLoad() {
        Intent intent = new Intent(getContext(), DownLoadSeriver.class);
        intent.putExtra("downloader_url", getLoad);
        getActivity().startService(intent);//启动服务
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);//绑定服务
    }
}
