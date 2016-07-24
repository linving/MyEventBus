package eventbus.distribute;

import eventbus.reflect.ReflectionHelper;

/**
 * @Author linyong
 * @Date 2016/7/24
 * @Time 14:08
 * <p/>
 * 异步分发事件
 */
public class AsynDistributeHandler implements DistributeHandler {
    public AsynDistributeHandler(SubscribeMethod subscribeMethod) {
        this.subscribeMethod = subscribeMethod;
    }

    private SubscribeMethod subscribeMethod = null;

    @Override
    public void handEvent(final Object parameter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReflectionHelper.invokeSubscribeMethod(subscribeMethod, parameter);
            }
        }).start();
    }
}
