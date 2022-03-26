package pub.developers.forum.infrastructure.transfer;

import org.springframework.util.ObjectUtils;
import pub.developers.forum.common.enums.*;
import pub.developers.forum.common.support.SafesUtil;
import pub.developers.forum.domain.entity.Message;
import pub.developers.forum.domain.entity.value.IdValue;
import pub.developers.forum.infrastructure.dal.dataobject.MessageDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qiangqiang.Bian
 * @create 2020/12/5
 * @desc
 **/
public class MessageTransfer {
    /**
     * 更新帖子信息
     * @param messageDO
     * @return message
     */
    public static Message toMessage(MessageDO messageDO) {
        if (ObjectUtils.isEmpty(messageDO)) {
            return null;
        }
        Message message = Message.builder()
                .title(messageDO.getTitle())
                .read(MessageReadEn.getEntity(messageDO.getRead()))
                .receiver(IdValue.builder()
                        .id(messageDO.getReceiver())
                        .type(IdValueTypeEn.getEntity(messageDO.getReceiverType()))
                        .build())
                .sender(IdValue.builder()
                        .id(messageDO.getSender())
                        .type(IdValueTypeEn.getEntity(messageDO.getSenderType()))
                        .build())
                .type(MessageTypeEn.getEntity(messageDO.getType()))
                .content(messageDO.getContent())
                .channel(MessageChannelEn.getEntity(messageDO.getChannel()))
                .contentType(MessageContentTypeEn.getEntity(messageDO.getContentType()))
                .build();
        message.setId(messageDO.getId());
        message.setCreateAt(messageDO.getCreateAt());
        message.setUpdateAt(messageDO.getUpdateAt());

        return message;
    }

    /**
     * 转移帖子信息
     * @param messageDOS
     * @return
     */
    public static List<Message> toMessages(List<MessageDO> messageDOS) {
        List<Message> res = new ArrayList<>();

        SafesUtil.ofList(messageDOS).forEach(messageDO -> {
            res.add(toMessage(messageDO));
        });

        return res;
    }
}
