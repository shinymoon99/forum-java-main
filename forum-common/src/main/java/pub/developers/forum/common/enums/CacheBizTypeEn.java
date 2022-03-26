package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了两个常量用来区别缓存类型.
 * @author Qiangqiang.Bian
 * @create 2020/10/20
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum CacheBizTypeEn {
    /**
     * 用户登录凭证 token
     */
    USER_LOGIN_TOKEN("USER_LOGIN_TOKEN", "用户登录凭证 token"),
    /**
     * 已使用标签
     */
    TAG_USED("TAG_USED", "已使用标签")
    ;

    private String value;
    private String desc;
}
