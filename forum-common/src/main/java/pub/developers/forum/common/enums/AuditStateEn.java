package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了三个常量用来判别贴子的审核状态.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum AuditStateEn {
    /**
     *待审核
     */
    WAIT("WAIT", "待审核"),
    /**
     * 审核通过
     */
    PASS("PASS", "审核通过"),
    /**
     * 审核拒绝
     */
    REJECT("REJECT", "审核拒绝"),
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“WAIT”、“PASS”、“REJECT”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static AuditStateEn getEntity(String value) {
        for (AuditStateEn entity : values()) {
            if (entity.getValue().equalsIgnoreCase(value)) {
                return entity;
            }
        }

        return null;
    }

}
