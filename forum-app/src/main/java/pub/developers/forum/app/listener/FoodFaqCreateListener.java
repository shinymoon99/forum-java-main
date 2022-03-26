package pub.developers.forum.app.listener;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pub.developers.forum.common.enums.FollowedTypeEn;
import pub.developers.forum.common.support.EventBus;
import pub.developers.forum.domain.entity.Faq;
import pub.developers.forum.domain.entity.UserFood;
import pub.developers.forum.domain.repository.UserFollowRepository;
import pub.developers.forum.domain.repository.UserFoodRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 问答创建监听，产生消息，连接记录相关用户和帖子id
 * @author Qiangqiang.Bian
 * @create 2020/12/3
 * @desc
 * 问答创建监听，产生消息，连接记录相关用户和帖子id
 **/
@Component
public class FoodFaqCreateListener extends EventBus.EventHandler<Faq> {

    @Resource
    private UserFollowRepository userFollowRepository;

    @Resource
    private UserFoodRepository userFoodRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.FAQ_CREATE;
    }

    @Override
    public void onMessage(Faq faq) {
        List<Long> followedIds = userFollowRepository.getAllFollowerIds(faq.getAuthorId(), FollowedTypeEn.USER);
        if (ObjectUtils.isEmpty(followedIds)) {
            return;
        }

        List<UserFood> userFoods = followedIds.stream().map(userId -> {
            return UserFood.builder()
                    .postsId(faq.getId())
                    .userId(userId)
                    .build();
        }).collect(Collectors.toList());

        userFoodRepository.batchSave(userFoods);
    }
}
