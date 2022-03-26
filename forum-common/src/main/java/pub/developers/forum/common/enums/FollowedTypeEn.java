package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义两个常量用于区别关注的类型.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum FollowedTypeEn {
    /**
     * 用户.
     */
    USER("USER", "用户"),
    /**
     * 帖子.
     */
    POSTS("POSTS", "帖子"),
    ;
    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“USER”、“POSTS”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static FollowedTypeEn getEntity(String value) {
        for (FollowedTypeEn contentType : values()) {
            if (contentType.getValue().equals(value)) {
                return contentType;
            }
        }
        return null;
    }
}
