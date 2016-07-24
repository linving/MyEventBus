package linving.myeventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Method;

import eventbus.EventBus;
import eventbus.annotation.Subscriber;
import eventbus.annotation.SubscriberType;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private Button bt_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getInstance().register(this);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt_post = (Button) findViewById(R.id.bt_post);
        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
    }


    @Subscriber(type = SubscriberType.SYNC)
    private void onEvent(Action action) {
        Log.d(TAG, "onEvent  = " + action.getMsg());
        bt_post.setText(action.getMsg());
    }

    @Subscriber(type = SubscriberType.SYNC)
    private void onEvent(Action2 action) {
        Log.d(TAG, "onEvent  = " + action.getMsg());
        bt_post.setText("Action2");
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unregister(this);
    }
}
