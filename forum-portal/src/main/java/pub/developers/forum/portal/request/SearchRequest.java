package pub.developers.forum.portal.request;

import lombok.Data;

/**
 * 搜索请求
 * @author Qiangqiang.Bian
 * @create 2020/12/2
 * @desc
 **/
@Data
public class SearchRequest extends BasePageRequest {

    private String key;
}
