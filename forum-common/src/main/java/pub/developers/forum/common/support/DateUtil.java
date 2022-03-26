package pub.developers.forum.common.support;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author lixiaoyang
 * @create 2022/3/20
 **/
public class DateUtil {

    /**
     * 以“yyyy-MM-dd HH:mm:ss”的形式格式化日期
     * @param date 被格式化的日期
     * @return
     */
    public static String toyyyyMMddHHmmss(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
