package linving.myeventbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import eventbus.EventBus;
import eventbus.annotation.Subscriber;
import eventbus.annotation.SubscriberType;

/**
 * @Author linyong
 * @Date 2016/7/24
 * @Time 14:41
 */
public class TestActivity extends Activity {
    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getInstance().register(this);
        setContentView(R.layout.activity_test);
        tv_test = (TextView) findViewById(R.id.tv_test);
        tv_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getInstance().post(new Action2(" Action "));
                finish();
            }
        });
    }

    @Subscriber(type = SubscriberType.SYNC)
    void onEvent(Action action) {
        tv_test.setText(action.getMsg());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unregister(this);
    }
}
