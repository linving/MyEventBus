package eventbus.distribute;

import java.lang.reflect.Method;

import eventbus.annotation.SubscriberType;

/**
 * @Author linyong
 * @Date 2016/7/24
 * @Time 13:22
 */
public class SubscribeMethod {

    //对象
    private Object object;
    // 方法
    private Method method;
    // 参数 (只支持一个参数的方法)
    private Class<?> parameters;
    //执行类型
    private SubscriberType subscriberType;


    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class<?> getParameters() {
        return parameters;
    }

    public void setParameters(Class<?> parameters) {
        this.parameters = parameters;
    }

    public SubscriberType getSubscriberType() {
        return subscriberType;
    }

    public void setSubscriberType(SubscriberType subscriberType) {
        this.subscriberType = subscriberType;
    }
}
