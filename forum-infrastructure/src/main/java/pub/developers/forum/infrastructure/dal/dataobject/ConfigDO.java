package pub.developers.forum.infrastructure.dal.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**界面布局
 * @author Qiangqiang.Bian
 * @create 2020/12/26
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigDO extends BaseDO {

    private String state;

    /**帖子类别*/
    private String type;

    /**帖子名*/
    private String name;

    /**帖子内容*/
    private String content;

    /**数据开始位置*/
    private Date startAt;

    /**数据结束位置*/
    private Date endAt;

    /** 创建长数据*/
    private Long creator;

}
