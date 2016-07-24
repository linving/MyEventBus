package eventbus.distribute;

import java.util.List;

import eventbus.EventBus;
import eventbus.reflect.ReflectionHelper;
import eventbus.annotation.SubscriberType;

/**
 * @Author linyong
 * @Date 2016/7/24
 * @Time 14:10
 */
public class DistributeManeger {
    private DistributeHandler distributeHandler;

    /**
     * 分发事件
     *
     * @param parameter
     */
    public void post(Object parameter) {
        List<SubscribeMethod> subscribeMethods = ReflectionHelper.getTypeSubscribeMethods(parameter, EventBus.getInstance().getSubscribeMethodList());
        for (SubscribeMethod subscribeMethod : subscribeMethods) {
            if (subscribeMethod.getSubscriberType().equals(SubscriberType.ASYN)) {
                distributeHandler = new AsynDistributeHandler(subscribeMethod);
                distributeHandler.handEvent(parameter);
            } else if (subscribeMethod.getSubscriberType().equals(SubscriberType.SYNC)) {
                distributeHandler = new SyncDistributeHandler(subscribeMethod);
                distributeHandler.handEvent(parameter);
            }
        }
    }

    /**
     * 注销
     *
     * @param obj
     */
    public void unregister(Object obj) {
        List<SubscribeMethod> subscribeMethods = ReflectionHelper.getTypeObjSubscribeMethod(obj, EventBus.getInstance().getSubscribeMethodList());
        for (SubscribeMethod subscribeMethod : subscribeMethods) {
            EventBus.getInstance().getSubscribeMethodList().remove(subscribeMethod);
        }

    }

}
