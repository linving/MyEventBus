package eventbus.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import eventbus.distribute.SubscribeMethod;
import eventbus.annotation.Subscriber;

/**
 * @Author linyong
 * @Date 2016/7/24
 * @Time 13:31
 */
public class ReflectionHelper {
    private static final String TAG = ReflectionHelper.class.getSimpleName();


    /**
     * 获取 含有 @Subscriber 的方法
     *
     * @param obj
     * @param subscribeMethodList
     */
    public static void getSubscribeMethods(Object obj, List<SubscribeMethod> subscribeMethodList) {
        Class clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Subscriber.class) && method.getParameterTypes().length == 1) {
                Subscriber subscriber = method.getAnnotation(Subscriber.class);
                Class parameters = method.getParameterTypes()[0];
                SubscribeMethod subscribeMethod = new SubscribeMethod();
                subscribeMethod.setObject(obj);
                subscribeMethod.setMethod(method);
                subscribeMethod.setParameters(parameters);
                subscribeMethod.setSubscriberType(subscriber.type());
                subscribeMethodList.add(subscribeMethod);
            }
        }
    }

    /**
     *
     * 根据 Post 函数里面的参数 获取 有这些参数的方法
     * @param parameter
     * @param subscribeMethodList
     * @return
     */
    public static List<SubscribeMethod> getTypeSubscribeMethods(Object parameter, List<SubscribeMethod> subscribeMethodList) {
        Class<?> clazz = parameter.getClass();
        List<SubscribeMethod> typeSubscribeMethodList = new ArrayList<>();
        for (SubscribeMethod subMethod : subscribeMethodList) {
            if (clazz.equals(subMethod.getParameters())) {
                typeSubscribeMethodList.add(subMethod);
            }
        }
        return typeSubscribeMethodList;
    }


    /**
     * 执行
     *
     * @param subscribeMethod
     * @param parameter
     */
    public static void invokeSubscribeMethod(SubscribeMethod subscribeMethod, Object parameter) {
        Method method = subscribeMethod.getMethod();
        Object object = subscribeMethod.getObject();
        try {
            method.setAccessible(true);
            method.invoke(object, parameter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据 obj 获取 列表
     *
     * @param obj
     * @param subscribeMethodList
     * @return
     */
    public static List<SubscribeMethod> getTypeObjSubscribeMethod(Object obj, List<SubscribeMethod> subscribeMethodList) {
        List<SubscribeMethod> typeSubscribeMethodList = new ArrayList<>();
        for (SubscribeMethod subMethod : subscribeMethodList) {
            if (obj.equals(subMethod.getObject())) {
                typeSubscribeMethodList.add(subMethod);
            }
        }
        return typeSubscribeMethodList;
    }


}
