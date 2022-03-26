package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了三个常量来区分用户id类型.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum IdValueTypeEn {
    /**
     * 系统
     */
    SYSTEM("SYSTEM", "系统"),
    /**
     *用户ID
     */
    USER_ID("USER_ID", "用户ID"),
    /**
     *邮箱
     */
    EMAIL("MAIL", "邮箱"),
    ;
    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“SYSTEM”、“SUER_ID”、“MAIL”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static IdValueTypeEn getEntity(String value) {
        for (IdValueTypeEn contentType : values()) {
            if (contentType.getValue().equals(value)) {
                return contentType;
            }
        }
        return null;
    }
}
