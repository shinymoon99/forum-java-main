package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了三个常量表示用户状态.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum UserStateEn {
    /**
     *未激活
     */
    UN_ACTIVATION("UN_ACTIVATION", "未激活"),
    /**
     * 启用
     */
    ENABLE("ENABLE", "启用"),
    /**
     * 禁用
     */
    DISABLE("DISABLE", "禁用"),
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“UN_ACTIVATION”、“ENABLE”、“DISABLE”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static UserStateEn getEntity(String value) {
        for (UserStateEn userSexEn : values()) {
            if (userSexEn.getValue().equalsIgnoreCase(value)) {
                return userSexEn;
            }
        }

        return null;
    }
}
