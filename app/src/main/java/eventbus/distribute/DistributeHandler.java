package eventbus.distribute;

/**
 * @Author linyong
 * @Date 2016/7/24
 * @Time 14:06
 */
public interface DistributeHandler {
    void handEvent(Object parameter);
}
