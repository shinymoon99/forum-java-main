package pub.developers.forum.app.listener;

import org.springframework.stereotype.Component;
import pub.developers.forum.common.support.EventBus;
import pub.developers.forum.domain.entity.OptLog;
import pub.developers.forum.domain.entity.User;
import pub.developers.forum.domain.repository.OptLogRepository;

import javax.annotation.Resource;

/**
 *  用户注册消息监听，并记录
 * @author Qiangqiang.Bian
 * @create 2020/10/22
 * @desc
 * 用户注册消息监听，并记录
 **/
@Component
public class OptLogUserRegisterListener extends EventBus.EventHandler<User> {

    @Resource
    private OptLogRepository optLogRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.USER_REGISTER;
    }

    @Override
    public void onMessage(User user) {

        // 保存操作记录
        optLogRepository.save(OptLog.createUserRegisterRecordLog(user.getId(), user));
    }
}
