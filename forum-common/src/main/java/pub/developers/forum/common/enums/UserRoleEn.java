package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pub.developers.forum.common.exception.BizException;

/**
 * 该枚举类定义了三个常量来判别用户角色.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum UserRoleEn {
    /**
     *用户
     */
    USER("USER", "用户"),
    /**
     * 管理员
     */
    ADMIN("ADMIN", "管理员"),
    /**
     * 超级管理员
     */
    SUPER_ADMIN("SUPER_ADMIN", "超级管理员"),
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“USER”、“ADMIN”、“SUPER_ADMIN”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static UserRoleEn getEntity(String value) {
        for (UserRoleEn entity : values()) {
            if (entity.getValue().equalsIgnoreCase(value)) {
                return entity;
            }
        }

        return null;
    }

    /**
     * 判断指定的用户是否拥有权限
     * @param value 要判断的用户角色的枚举实例的值（“USER”、“ADMIN”、“SUPER_ADMIN”）.
     * @return 有权限返回true，没有返回false.
     */
    public boolean hasPermission(String value) {
        if (USER.equals(this)) {
            return false;
        }

        UserRoleEn authRole = getEntity(value);
        if (ADMIN.equals(this) && (ADMIN.equals(authRole) || SUPER_ADMIN.equals(authRole))) {
            return false;
        }

        if (SUPER_ADMIN.equals(this) && SUPER_ADMIN.equals(authRole)) {
            return false;
        }

        return true;
    }
}
