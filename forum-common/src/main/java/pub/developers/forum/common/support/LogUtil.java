package pub.developers.forum.common.support;

import org.slf4j.Logger;
import org.slf4j.helpers.MessageFormatter;

/**
 * 该工具类定义了一些记录日志的方法
 * @author lixiaoyang
 * @create 2022/3/20
 **/
public class LogUtil {

    /**
     * 记录日志信息
     * @param logger 日志对象
     * @param format 格式信息
     * @param args 要输出的参数
     */
    public static void info(Logger logger, String format, Object... args) {
        logger.info(getMsg(format, args));
    }

    /**
     * 记录日志信息
     * @param logger 日志对象
     * @param msg 要记录的日志信息
     */
    public static void info(Logger logger, String msg) {
        logger.info(getMsg(msg));
    }

    /**
     * 记录日志
     * @param logger 日志对象
     * @param throwable 记录错误或异常对象
     * @param format 日志格式信息
     * @param args 要输出的参数
     */
    public static void info(Logger logger, Throwable throwable, String format, Object... args) {
        logger.info(getMsg(format, args), throwable);
    }

    /**
     * 记录日志
     * @param logger 日志对象
     * @param msg 日志信息
     * @param throwable 要记录的错误或异常对象
     */
    public static void info(Logger logger, String msg, Throwable throwable) {
        logger.info(getMsg(msg), throwable);
    }

    /**
     * 返回格式化后的日志信息
     * @param format 日志格式信息
     * @param arguments 要记录的参数
     * @return 格式化后的日志信息
     */
    private static String getMsg(String format, Object... arguments) {
        StringBuilder sb = new StringBuilder()
                .append("[traceId-")
                .append(RequestContext.getTraceId())
                .append("] - ");

        if (null != arguments && arguments.length > 0) {
            sb.append(MessageFormatter.arrayFormat(format, arguments).getMessage());
        }else{
            sb.append(format);
        }

        return sb.toString();
    }
}
