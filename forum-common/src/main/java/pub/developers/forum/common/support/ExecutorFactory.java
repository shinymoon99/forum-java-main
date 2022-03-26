package pub.developers.forum.common.support;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 *线程工厂类
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Slf4j
public class ExecutorFactory {

    /**
     * 获取线程池
     * @param cls 将该类名作为线程池名称
     * @param fixedThreads 线程数量.
     * @return
     */
    public static ExecutorService getExecutorService(Class<?> cls, int fixedThreads) {
        return new ThreadPoolExecutor(fixedThreads, fixedThreads,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(512),
                new ThreadFactoryBuilder()
                        .setNameFormat(cls.getSimpleName() + "-%d")
                        .setUncaughtExceptionHandler(ExecutorFactory.getCommonHandler())
                        .setDaemon(false)
                        .build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 返回一个未捕捉到异常句柄
     * @return
     */
    private static Thread.UncaughtExceptionHandler getCommonHandler() {
        return (t, ex) -> {
            log.error("GroupName:[{}], ThreadName:[{}]. "
                    , t.getThreadGroup().getName()
                    , t.getName());
            if (ex != null) {
                log.error("Cause:[{}], Message:[{}]. "
                        , ex.getCause()
                        , ex.getMessage()
                        , ex);
            }
        };
    }

}

