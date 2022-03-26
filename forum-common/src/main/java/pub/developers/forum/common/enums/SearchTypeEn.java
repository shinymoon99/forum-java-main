package pub.developers.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 该枚举类定义了一个常量来标识搜索类型.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Getter
@AllArgsConstructor
public enum SearchTypeEn {
    /**
     * 帖子
     */
    POSTS("POSTS", "帖子"),
    ;

    private String value;
    private String desc;
}
