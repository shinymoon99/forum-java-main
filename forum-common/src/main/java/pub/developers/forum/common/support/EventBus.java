package pub.developers.forum.common.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * 事件处理类
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Slf4j
@Component
public class EventBus {

    /**
     * 消息类型枚举
     */
    public enum Topic {
        /**
         *用户登录
         */
        USER_LOGIN,
        /**
         * 用户注册
         */
        USER_REGISTER,
        /**
         * 用户登出
         */
        USER_LOGOUT,
        /**
         * 用户关注
         */
        USER_FOLLOW,
        /**
         * 用户取消关注
         */
        USER_CANCEL_FOLLOW,

        /**
         * 文章创建
         */
        ARTICLE_CREATE,
        /**
         * 文章更新
         */
        ARTICLE_UPDATE,

        /**
         * 贴子信息
         */
        POSTS_INFO,
        /**
         * 删除贴子
         */
        POSTS_DELETE,

        /**
         * 创建问答
         */
        FAQ_CREATE,
        /**
         * 更新问答
         */
        FAQ_UPDATE,

        /**
         * 创建点赞/收藏
         */
        APPROVAL_CREATE,

        /**
         * 创建评论
         */
        COMMENT_CREATE,
        ;
    }

    /**
     * 事件/事件处理 映射
     */
    private static final Map<Topic, List<EventBus.EventHandler>> EVENT_HANDLER_MAP = new ConcurrentHashMap<>();

    /**
     * 线程池
     */
    private final static ExecutorService executorService = ExecutorFactory.getExecutorService(EventBus.class, 4);

    /**
     *关闭线程池
     */
    @PreDestroy
    public void post() {
        executorService.shutdown();
    }

    /**
     * 触发事件，默认异步执行
     * @param eventEn
     * @param message
     */
    public static void emit(final Topic eventEn, final Object message) {
        processEmitEvent(eventEn, message, true);
    }

    /**
     * 同步触发事件
     * @param eventEn
     * @param message
     */
    public static void emitSync(final Topic eventEn, final Object message) {
        processEmitEvent(eventEn, message, false);
    }

    /**
     * 执行处理的事件
     * @param eventEn
     * @param message
     */
    private static void processEmitEvent(Topic eventEn, Object message, Boolean async) {
        log.info("Bus on event=[{}] message=[{}]", eventEn, message);
        List<EventBus.EventHandler> eventHandlers = EVENT_HANDLER_MAP.get(eventEn);
        if (null == eventHandlers) {
            log.warn("emit [{}] event, but not handler.", eventEn);
            return;
        }

        eventHandlers.forEach(eventHandler -> {
            if (async) {
                executorService.submit(() -> {
                    try {
                        eventHandler.onMessage(message);
                    } catch (Exception e) {
                        log.error("handler [{}] async process event [{}] error.", eventHandler.getClass(), eventEn, e);
                    }
                });
            } else {
                try {
                    eventHandler.onMessage(message);
                } catch (Exception e) {
                    log.error("handler [{}] sync process event [{}] error.", eventHandler.getClass(), eventEn, e);
                }
            }
        });
    }

    /**
     * 监听事件
     * @param eventEn
     * @param eventHandler
     */
    private static synchronized void on(Topic eventEn, EventBus.EventHandler eventHandler) {
        List<EventBus.EventHandler> eventHandlers = EVENT_HANDLER_MAP.get(eventEn);

        if (eventHandlers == null) {
            eventHandlers = new ArrayList<>();
        }

        eventHandlers.add(eventHandler);
        EVENT_HANDLER_MAP.put(eventEn, eventHandlers);
    }

    public static abstract class EventHandler<T> implements InitializingBean {

        public abstract Topic topic();

        public abstract void onMessage(T message);

        @Override
        public void afterPropertiesSet() throws Exception {
            EventBus.on(topic(), this);
        }
    }
}
