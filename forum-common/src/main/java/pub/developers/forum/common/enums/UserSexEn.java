package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pub.developers.forum.common.exception.BizException;

/**
 * 该枚举类定义了三个常量用来表示用户性别.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum UserSexEn {
    /**
     *未知
     */
    UNKNOWN("UNKNOWN", "未知"),
    /**
     * 男
     */
    MAN("MAN", "男"),
    /**
     * 女
     */
    WOMAN("WOMAN", "女"),
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“UNKNOWN”、“MAN”、“WOMAN”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static UserSexEn getEntity(String value) {
        for (UserSexEn userSexEn : values()) {
            if (userSexEn.getValue().equalsIgnoreCase(value)) {
                return userSexEn;
            }
        }

        return null;
    }
}
