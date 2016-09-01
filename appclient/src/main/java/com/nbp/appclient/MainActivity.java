package com.nbp.appclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nbp.serviceprovider.IAppServiceRemoteBinder;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Intent serviceIntent;
    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button startService = (Button) findViewById(R.id.btn_start_service);
//        Button stopService = (Button) findViewById(R.id.btn_stop_service);

        etInput = (EditText) findViewById(R.id.et_input);

        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.nbp.serviceprovider","com.nbp.serviceprovider.AppService"));

        findViewById(R.id.btn_start_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_service).setOnClickListener(this);
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);
        findViewById(R.id.btn_sync).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_start_service:
                startService(serviceIntent);
                break;
            case R.id.btn_stop_service:
                stopService(serviceIntent);
                break;

            case R.id.btn_bind_service:
                bindService(serviceIntent,this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(this);
                binder = null;
                break;

            case R.id.btn_sync:

                if (binder != null){
                    try {
                        binder.setData(etInput.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;




        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

        System.out.println("已绑定服务");
        System.out.println(service);

//        binder = (IAppServiceRemoteBinder) service;

        binder = IAppServiceRemoteBinder.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private IAppServiceRemoteBinder binder = null;
}
