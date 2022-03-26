package pub.developers.forum.app.listener;

import org.springframework.stereotype.Component;
import pub.developers.forum.common.support.EventBus;
import pub.developers.forum.domain.entity.BasePosts;
import pub.developers.forum.domain.repository.PostsRepository;

import javax.annotation.Resource;

/**
 * 文章信息监听，判断是否修改
 * @author Qiangqiang.Bian
 * @create 2020/11/10
 * @desc
 * 文章信息监听，判断是否修改
 **/
@Component
public class PostsInfoListener extends EventBus.EventHandler<BasePosts> {

    @Resource
    private PostsRepository postsRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.POSTS_INFO;
    }

    @Override
    public void onMessage(BasePosts posts) {
        postsRepository.increaseViews(posts.getId(), posts.getUpdateAt());
    }
}
