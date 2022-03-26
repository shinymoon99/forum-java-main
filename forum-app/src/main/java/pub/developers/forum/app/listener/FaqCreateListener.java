package pub.developers.forum.app.listener;

import org.springframework.stereotype.Component;
import pub.developers.forum.common.support.EventBus;
import pub.developers.forum.domain.entity.Faq;
import pub.developers.forum.domain.entity.Tag;
import pub.developers.forum.domain.repository.TagRepository;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 问答创建监听，产生消息，同时会增加话题引用数
 * @author Qiangqiang.Bian
 * @create 2020/11/24
 * @desc
 * 问答创建监听，产生消息，同时会增加话题引用数
 **/
@Component
public class FaqCreateListener extends EventBus.EventHandler<Faq> {

    @Resource
    private TagRepository tagRepository;

    @Override
    public EventBus.Topic topic() {
        return EventBus.Topic.FAQ_CREATE;
    }

    @Override
    public void onMessage(Faq faq) {
        Set<Long> tagIds = faq.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        tagRepository.increaseRefCount(tagIds);
    }
}
