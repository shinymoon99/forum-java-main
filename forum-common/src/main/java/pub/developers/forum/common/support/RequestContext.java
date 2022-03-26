package pub.developers.forum.common.support;

import org.springframework.util.ObjectUtils;

/**
 * 操作请求的trade id的工具类
 * @author lixiaoyang
 * @create 2022/3/20
 **/
public class RequestContext {

    /**
     * trace id
     */
    private static final ThreadLocal<String> REQUEST_TRACE_ID = new ThreadLocal<>();

    /**
     * 为当前请求生成 trade id
     * @param
     */
    public static void init() {
        if (ObjectUtils.isEmpty(REQUEST_TRACE_ID.get())) {
            REQUEST_TRACE_ID.set(StringUtil.generateUUID());
        }
    }

    /**
     * 获取当前请求 trade id
     * @return
     */
    public static String getTraceId() {
        return ObjectUtils.isEmpty(REQUEST_TRACE_ID.get()) ? "" : REQUEST_TRACE_ID.get();
    }

    /**
     * 清楚线程的值
     */
    public static void removeAll() {
        REQUEST_TRACE_ID.remove();
    }

}
