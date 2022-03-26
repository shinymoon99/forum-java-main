package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了两个常量来区分布局类型.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum ConfigTypeEn {

    /**
     * 首页轮播图
     */
    HOME_CAROUSEL("HOME_CAROUSEL", "首页轮播图"),

    /**
     * 侧边栏轮播图
     */
    SIDEBAR_CAROUSEL("SIDEBAR_CAROUSEL", "侧边栏轮播图"),
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“HOME_CAROUSEL”、“SIDEBAR_CAROUSEL”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static ConfigTypeEn getEntity(String value) {
        for (ConfigTypeEn contentType : values()) {
            if (contentType.getValue().equals(value)) {
                return contentType;
            }
        }
        return null;
    }

}
