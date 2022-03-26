package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了一些用户操作信息类型的常量.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum MessageTypeEn {
    /**
     * 新用户注册通知管理员
     */
    USER_REGISTER_NOTIFY_ADMIN("USER_REGISTER_NOTIFY_ADMIN", "新用户注册通知管理员"),
    /**
     *点赞文章
     */
    APPROVAL_ARTICLE("APPROVAL_ARTICLE", "点赞文章"),
    /**
     *评论文章
     */
    COMMENT_ARTICLE("COMMENT_ARTICLE", "评论文章"),
    /**
     *关注问答
     */

    APPROVAL_FAQ("APPROVAL_FAQ", "关注问答"),
    /**
     *评论问答
     */
    COMMENT_FAQ("COMMENT_FAQ", "评论问答"),
    /**
     *用户关注
     */

    FOLLOW_USER("FOLLOW_USER", "用户关注"),
    ;
    private String value;
    private String desc;

    /**
     * 返回带有指定值的该类型的枚举常量.
     * @param value 要返回的枚举实例的值.
     * @return 该类型的枚举常量,如果没有指定的值所对应的枚举常量则返回null.
     */
    public static MessageTypeEn getEntity(String value) {
        for (MessageTypeEn contentType : values()) {
            if (contentType.getValue().equals(value)) {
                return contentType;
            }
        }
        return null;
    }

    /**
     * 返回带有指定描述信息的该类型的枚举常量.
     * @param desc 信息类型的描述
     * @return 该类型的枚举常量,如果没有指定的描述信息所对应的枚举常量则返回null.
     */
    public static MessageTypeEn getEntityByDesc(String desc) {
        for (MessageTypeEn contentType : values()) {
            if (contentType.getDesc().equals(desc)) {
                return contentType;
            }
        }
        return null;
    }

}
