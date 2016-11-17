package com.org.iii.shine06;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.TextView;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private UIHandler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiHandler = new UIHandler();
        tv = (TextView)findViewById(R.id.tv);
    }

    public void test1(View v){
        MyThread mt1 = new MyThread();
        mt1.start();
    }
    private class MyThread extends Thread {
        @Override
        public void run() {
            for (int i=0; i<10; i++){
                Message mesg = new Message();
                Bundle data = new Bundle();
                data.putInt("key1",i);
                mesg.setData(data);
                uiHandler.sendMessage(mesg);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle data = msg.getData();
            int i = data.getInt("key1");
            tv.setText("i ="+i);

        }
    }

}
