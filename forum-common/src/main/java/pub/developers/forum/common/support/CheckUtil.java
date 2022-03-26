package pub.developers.forum.common.support;

import pub.developers.forum.common.enums.ErrorCodeEn;
import pub.developers.forum.common.exception.BizException;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;

/**
 *该工具类用于对一些类和布尔值进行检查，并抛出相关异常信息.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
public class CheckUtil {

    private CheckUtil() {
    }

    /**
     * 检查请求路径参数是否为空，如果为空则抛出异常.
     * @param o 被检查的对象
     * @param message 向异常中添加的信息
     */
    public static void checkParamToast(Object o, String message) {
        if (ObjectUtils.isEmpty(o)) {
            throw new BizException(ErrorCodeEn.PARAM_CHECK_ERROR.getCode(),
                    MessageFormat.format(ErrorCodeEn.PARAM_CHECK_ERROR.getMessage(), message));
        }
    }

    /**
     * 检查请求路径参数是否为空，如果为空则抛出异常.
     * @param o 被检查的对象
     * @param message 向抛出异常中添加的信息
     */
    public static void checkEmptyToast(Object o, String message) {
        if (ObjectUtils.isEmpty(o)) {
            throw new BizException(ErrorCodeEn.CHECK_ERROR_TOAST.getCode(),
                    MessageFormat.format(ErrorCodeEn.CHECK_ERROR_TOAST.getMessage(), message));
        }
    }

    /**
     * 检查给定的对象是否为空，如果为空则抛出异常.
     * @param o         被检查的对象
     * @param errorCode 抛出的异常信息
     * @desc 检查是否为空，为空则报异常
     */
    public static void isEmpty(Object o, ErrorCodeEn errorCode) {
        if (ObjectUtils.isEmpty(o)) {
            throw new BizException(errorCode);
        }
    }

    /**
     * 检查给定的对象是否为空，如果不为空则抛出异常.
     * @param o 被检查的对象
     * @param errorCode 抛出的异常信息
     */
    public static void isNotEmpty(Object o, ErrorCodeEn errorCode) {
        if (!ObjectUtils.isEmpty(o)) {
            throw new BizException(errorCode);
        }
    }

    /**
     * 检查给定布尔值是否为false，如果是则抛出异常.
     * @param b 给检查的布尔值
     * @param errorCode 抛出的异常信息
     */
    public static void isFalse(Boolean b, ErrorCodeEn errorCode) {
        if (!b) {
            throw new BizException(errorCode);
        }
    }

    /**
     * 检查给定布尔值是否为true，如果是则抛出异常.
     * @param b 给检查的布尔值
     * @param errorCode 抛出的异常信息
     */
    public static void isTrue(Boolean b, ErrorCodeEn errorCode) {
        if (b) {
            throw new BizException(errorCode);
        }
    }

}