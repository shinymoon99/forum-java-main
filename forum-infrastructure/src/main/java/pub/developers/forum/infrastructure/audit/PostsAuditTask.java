package pub.developers.forum.infrastructure.audit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pub.developers.forum.common.enums.AuditStateEn;
import pub.developers.forum.common.support.SafesUtil;
import pub.developers.forum.infrastructure.dal.dao.PostsDAO;
import pub.developers.forum.infrastructure.dal.dataobject.PostsDO;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author lllzzz
 * @create 2022/3/20
 * @desc 帖子自动审核通过
 **/
@Service

public class PostsAuditTask {

    @Resource
    private PostsDAO postsDAO;
    /**
     * 每隔两秒执行一次。
     */
    @Scheduled(cron = "0/2 * * * * ? ")
    public void task() {
        List<PostsDO> postsDOS = postsDAO.query(PostsDO.builder()
                .auditState(AuditStateEn.WAIT.getValue())
                .build());
        SafesUtil.ofList(postsDOS).forEach(postsDO -> {
            postsDO.setAuditState(AuditStateEn.PASS.getValue());
            postsDAO.update(postsDO);
        });
    }

}
