package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了两个常量来判别用户来源.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum UserSourceEn {
    /**
     *来自注册
     */
    REGISTER("REGISTER", "注册"),
    /**
     *来自github
     */
    GITHUB("GITHUB", "github"),
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“REGISTER”、“GITHUB”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static UserSourceEn getEntity(String value) {
        for (UserSourceEn en : values()) {
            if (en.getValue().equalsIgnoreCase(value)) {
                return en;
            }
        }

        return null;
    }
}
