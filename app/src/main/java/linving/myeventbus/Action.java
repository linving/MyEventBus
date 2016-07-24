package linving.myeventbus;

/**
 * @Author linyong
 * @Date 2016/7/24
 * @Time 13:16
 */
public class Action {
    private String msg;

    public Action(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
