package eventbus.distribute;

import eventbus.reflect.ReflectionHelper;

/**
 * @Author linyong
 * @Date 2016/7/24
 * @Time 14:07
 * 同步分发事件
 */
public class SyncDistributeHandler implements DistributeHandler {

    private SubscribeMethod subscribeMethod = null;

    public SyncDistributeHandler(SubscribeMethod subscribeMethod) {
        this.subscribeMethod = subscribeMethod;
    }

    @Override
    public void handEvent(Object parameter) {
        ReflectionHelper.invokeSubscribeMethod(subscribeMethod, parameter);
    }
}
