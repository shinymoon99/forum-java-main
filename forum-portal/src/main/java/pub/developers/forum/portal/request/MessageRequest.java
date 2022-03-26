package pub.developers.forum.portal.request;

import lombok.Data;

/**
 * 消息请求
 * @author Qiangqiang.Bian
 * @create 2020/12/5
 * @desc
 **/
@Data
public class MessageRequest extends BasePageRequest {

    private String type;

}
