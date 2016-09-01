package com.nbp.serviceprovider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //在onstart中启动服务
//        startService(new Intent(this,AppService.class));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        //在ondestroy中停止服务
//        stopService(new Intent(this,AppService.class));
    }
}
