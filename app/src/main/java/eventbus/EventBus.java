package eventbus;

import java.util.ArrayList;
import java.util.List;

import eventbus.distribute.DistributeManeger;
import eventbus.distribute.SubscribeMethod;
import eventbus.reflect.ReflectionHelper;

/**
 * @Author linyong
 * @Date 2016/7/21
 * @Time 13:59
 */
public class EventBus {
    private static EventBus instance;

    private List<SubscribeMethod> subscribeMethodList = new ArrayList<>();

    private DistributeManeger distributeManeger = new DistributeManeger();

    /**
     * @return
     */
    public static EventBus getInstance() {
        if (null == instance)
            newInstance();
        return instance;
    }

    /**
     * 初始化实例
     */
    private static synchronized void newInstance() {
        if (null == instance) {
            instance = new EventBus();
        }
    }

    /**
     * 注册某对象
     * 1 . 获取含有注解的方法 参数
     *
     * @param obj
     */
    public void register(Object obj) {
        ReflectionHelper.getSubscribeMethods(obj, subscribeMethodList);
    }

    /**
     * 发送事件
     *
     * @param obj
     */
    public void post(Object obj) {
        distributeManeger.post(obj);
    }

    /**
     * 反注册某对象
     *
     * @param obj
     */
    public void unregister(Object obj) {
        distributeManeger.unregister(obj);
    }

    /**
     *
     * @return
     */
    public List<SubscribeMethod> getSubscribeMethodList() {
        return subscribeMethodList;
    }
}
