package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pub.developers.forum.common.exception.BizException;

/**
 * 该枚举类定义了两个常量用来区分内容类型.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@AllArgsConstructor
@Getter
public enum ContentTypeEn {
    /**
     * html富文本.
     */
    HTML("HTML", "html富文本"),
    /**
     * markdown内容.
     */
    MARKDOWN("MARKDOWN", "markdown内容"),
    ;

    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值（“HTML”、“MARKDOWN”）.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static ContentTypeEn getEntity(String value) {
        for (ContentTypeEn contentType : values()) {
            if (contentType.getValue().equals(value)) {
                return contentType;
            }
        }
        throw new BizException(ErrorCodeEn.CONTENT_TYPE_NOT_EXIST);
    }
}
