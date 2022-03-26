package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了两个枚举常量用来判定文章类型范围.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum ArticleTypeScopeEn {
    /**
     *官方
     */
    OFFICIAL("OFFICIAL", "官方"),
    /**
     * 用户
     */
    USER("USER", "用户"),
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value  要返回枚举实例的值（“OFFICIAL”、“USER”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static ArticleTypeScopeEn getEntity(String value) {
        for (ArticleTypeScopeEn entity : values()) {
            if (entity.getValue().equalsIgnoreCase(value)) {
                return entity;
            }
        }

        return null;
    }

}
