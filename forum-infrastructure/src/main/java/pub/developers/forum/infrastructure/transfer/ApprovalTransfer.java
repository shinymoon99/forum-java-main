package pub.developers.forum.infrastructure.transfer;

import pub.developers.forum.common.enums.FollowedTypeEn;
import pub.developers.forum.domain.entity.Approval;
import pub.developers.forum.infrastructure.dal.dataobject.UserFollowDO;

/**
 * @author Qiangqiang.Bian
 * @create 2020/12/1
 * @desc
 **/
public class ApprovalTransfer {
    /**
     *获取posts的值  postsid 和userid
     * @param approval
     * @return
     */
    public static UserFollowDO toUserFollowDO(Approval approval) {
        return UserFollowDO.builder()
                .followedType(FollowedTypeEn.POSTS.getValue())
                .followed(approval.getPostsId())
                .follower(approval.getUserId())
                .build();
    }

    /**
     *更新发帖时间
     * @param userFollowDO
     * @return
     */
    public static Approval toApproval(UserFollowDO userFollowDO) {
        Approval approval = Approval.builder()
                .userId(userFollowDO.getFollower())
                .postsId(userFollowDO.getFollowed())
                .build();
        approval.setCreateAt(userFollowDO.getCreateAt());
        approval.setId(userFollowDO.getId());
        approval.setUpdateAt(userFollowDO.getUpdateAt());

        return approval;
    }
}
