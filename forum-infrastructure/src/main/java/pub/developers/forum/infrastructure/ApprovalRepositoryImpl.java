package pub.developers.forum.infrastructure;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import pub.developers.forum.common.enums.FollowedTypeEn;
import pub.developers.forum.domain.entity.Approval;
import pub.developers.forum.domain.repository.ApprovalRepository;
import pub.developers.forum.infrastructure.dal.dao.UserFollowDAO;
import pub.developers.forum.infrastructure.dal.dataobject.UserFollowDO;
import pub.developers.forum.infrastructure.transfer.ApprovalTransfer;

import javax.annotation.Resource;
import java.util.List;

/**
 * 同意/收藏实现
 * @author Qiangqiang.Bian
 * @create 2020/12/1
 * @desc
 **/
@Repository
public class ApprovalRepositoryImpl implements ApprovalRepository {

    @Resource
    private UserFollowDAO userFollowDAO;

    @Override
    /**
     * 保存帖子
     */
    public void save(Approval approval) {
        UserFollowDO userFollowDO = ApprovalTransfer.toUserFollowDO(approval);
        userFollowDO.initBase();

        userFollowDAO.insert(userFollowDO);
    }

    /**
     * 删除帖子
     * @param approvalId
     */
    @Override
    public void delete(Long approvalId) {
        userFollowDAO.delete(approvalId);
    }

    /**
     * 获取关注者ID
     * @param postsId
     * @param userId
     * @return
     */
    @Override
    public Approval get(Long postsId, Long userId) {
        List<UserFollowDO> userFollowDOS = userFollowDAO.query(UserFollowDO.builder()
                .follower(userId)
                .followed(postsId)
                .followedType(FollowedTypeEn.POSTS.getValue())
                .build());
        if (ObjectUtils.isEmpty(userFollowDOS)) {
            return null;
        }

        return ApprovalTransfer.toApproval(userFollowDOS.get(0));
    }
}
