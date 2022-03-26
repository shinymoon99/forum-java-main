package pub.developers.forum.app.listener;

import org.springframework.stereotype.Component;
import pub.developers.forum.common.support.EventBus;
import pub.developers.forum.domain.entity.BasePosts;
import pub.developers.forum.domain.service.SearchService;

import javax.annotation.Resource;

/**
 * 查询帖子删除消息监听，并且保存
 * @author Qiangqiang.Bian
 * @create 2020/12/3
 * @desc
 * 查询帖子删除消息监听，并且保存
 **/
@Component
public class SearchPostsDeleteListener extends EventBus.EventHandler<BasePosts> {

    @Resource
    private SearchService searchService;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.POSTS_DELETE;
    }

    @Override
    public void onMessage(BasePosts basePosts) {
        searchService.deleteByPostsId(basePosts.getId());
    }
}
