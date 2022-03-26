package pub.developers.forum.common.enums;

import lombok.Getter;

/**
 * 该枚举类定义了两个常量用来判断是否删除.
 * @author Qiangqiang.Bian
 * @create 20/7/23
 * @desc
 **/
public enum IsDeletedEn {
    /**
     * 删除
     */
    DELETED(1),
    /**
     * 不删除
     */
    NOT_DELETED(0),
    ;

    @Getter
    private Integer value;

    IsDeletedEn(Integer value) {
        this.value = value;
    }
}
