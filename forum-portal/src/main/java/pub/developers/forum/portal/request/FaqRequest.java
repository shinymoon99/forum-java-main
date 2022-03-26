package pub.developers.forum.portal.request;

import lombok.Data;

/**
 * 问答请求
 * @author Qiangqiang.Bian
 * @create 2020/10/25
 * @desc
 **/
@Data
public class FaqRequest extends BasePageRequest {

    private String type;

}
