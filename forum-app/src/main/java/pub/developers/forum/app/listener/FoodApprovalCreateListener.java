package pub.developers.forum.app.listener;

import org.springframework.stereotype.Component;
import pub.developers.forum.app.support.Pair;
import pub.developers.forum.common.support.EventBus;
import pub.developers.forum.domain.entity.UserFood;
import pub.developers.forum.domain.repository.UserFoodRepository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 点赞监听，产生消息，记录显示点赞的用户id和帖子id
 * @author Qiangqiang.Bian
 * @create 2020/12/3
 * @desc
 * 点赞监听，产生消息，记录显示点赞的用户id和帖子id
 **/
@Component
public class FoodApprovalCreateListener extends EventBus.EventHandler<Pair<Long>> {

    @Resource
    private UserFoodRepository userFoodRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.APPROVAL_CREATE;
    }

    @Override
    public void onMessage(Pair<Long> pair) {
        Long userId = pair.getValue0();
        Long postsId = pair.getValue1();

        List<UserFood> userFoods = Arrays.asList(UserFood.builder()
                .postsId(postsId)
                .userId(userId)
                .build());

        userFoodRepository.batchSave(userFoods);
    }
}
