package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了两个常量来区别信息通道.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@AllArgsConstructor
@Getter
public enum MessageChannelEn {
    /**
     * 站内信息.
     */
    STATION_LETTER("STATION_LETTER", "站内信"),
    /**
     * 邮件.
     */
    MAIL("MAIL", "邮件")
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“STATION_LETTER”、“MAIL”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static MessageChannelEn getEntity(String value) {
        for (MessageChannelEn contentType : values()) {
            if (contentType.getValue().equals(value)) {
                return contentType;
            }
        }
        return null;
    }
}
