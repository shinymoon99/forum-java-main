package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了用户登录、登出和注册的常量.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum OptLogTypeEn {
    /**
     *用户登录记录
     */
    USER_LOGIN("USER_LOGIN", "用户登录记录"),
    /**
     * 用户登出记录
     */
    USER_LOGOUT("USER_LOGOUT", "用户登出记录"),
    /**
     * 用户注册记录
     */
    USER_REGISTER("USER_REGISTER", "用户注册记录"),
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“USER_LOGIN”、“USER_LOGOUT”、“USER_REGISTER”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static OptLogTypeEn getEntity(String value) {
        for (OptLogTypeEn userSexEn : values()) {
            if (userSexEn.getValue().equalsIgnoreCase(value)) {
                return userSexEn;
            }
        }

        return null;
    }
}
