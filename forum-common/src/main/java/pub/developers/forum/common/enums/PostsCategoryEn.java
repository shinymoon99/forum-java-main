package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了两个常量来判别贴子种类.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum PostsCategoryEn {
    /**
     * 文章
     */
    ARTICLE("ARTICLE", "文章"),
    /**
     * 问答
     */
    FAQ("FAQ", "问答"),
    ;
    private String value;
    private String desc;
}
