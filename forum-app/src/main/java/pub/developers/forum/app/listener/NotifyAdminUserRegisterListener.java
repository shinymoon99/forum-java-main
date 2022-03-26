package pub.developers.forum.app.listener;

import org.springframework.stereotype.Component;
import pub.developers.forum.common.enums.MessageChannelEn;
import pub.developers.forum.common.enums.MessageTypeEn;
import pub.developers.forum.common.support.EventBus;
import pub.developers.forum.domain.entity.Message;
import pub.developers.forum.domain.entity.User;
import pub.developers.forum.domain.service.MessageService;

import javax.annotation.Resource;

/**
 *  通知管理员用户注册
 * @author Qiangqiang.Bian
 * @create 2020/12/4
 * @desc
 * 通知管理员用户注册
 **/
@Component
public class NotifyAdminUserRegisterListener extends EventBus.EventHandler<User> {

    @Resource
    private MessageService messageService;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.USER_REGISTER;
    }

    @Override
    public void onMessage(User user) {

        // 发送消息通知
        messageService.send(Message.builder()
                .channel(MessageChannelEn.MAIL)
                .type(MessageTypeEn.USER_REGISTER_NOTIFY_ADMIN)
//                .sender(IdValue.builder()
//                        .id()
//                        .name()
//                        .type(IdValueTypeEn.MAIL)
//                        .build())
//                .receiver(IdValue.builder()
//                        .id()
//                        .name()
//                        .type(IdValueTypeEn.MAIL)
//                        .build())
                .title("")
                .content("")
                .build());
    }
}
