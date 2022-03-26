package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了两个常量判别信息是否已读.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum MessageReadEn {
    /**
     * 已读
     */
    YES("YES", "已读"),
    /**
     * 未读
     */
    NO("NO", "未读")
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“HTML”、“TEXT”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static MessageReadEn getEntity(String value) {
        for (MessageReadEn contentType : values()) {
            if (contentType.getValue().equals(value)) {
                return contentType;
            }
        }
        return null;
    }

}
