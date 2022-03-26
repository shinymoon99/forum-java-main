package pub.developers.forum.portal.request;

import lombok.Data;

/**
 * 个人主页请求
 * @author Qiangqiang.Bian
 * @create 2020/10/25
 * @desc
 **/
@Data
public class UserRequest extends BasePageRequest {

    private String type;

}
