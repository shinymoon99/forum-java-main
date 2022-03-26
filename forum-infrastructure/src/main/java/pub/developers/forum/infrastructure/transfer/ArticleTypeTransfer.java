package pub.developers.forum.infrastructure.transfer;

import org.springframework.util.ObjectUtils;
import pub.developers.forum.common.enums.ArticleTypeScopeEn;
import pub.developers.forum.common.enums.AuditStateEn;
import pub.developers.forum.domain.entity.ArticleType;
import pub.developers.forum.infrastructure.dal.dataobject.ArticleTypeDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Qiangqiang.Bian
 * @create 2020/10/31
 * @desc
 **/
public class ArticleTypeTransfer {
    /**
     *获取审核状态  创建人  描述  名称 引用统计 作用域
     * @param articleType
     * @return
     */
    public static ArticleTypeDO toArticleTypeDO(ArticleType articleType) {
        ArticleTypeDO articleTypeDO = ArticleTypeDO.builder()
                .auditState(ObjectUtils.isEmpty(articleType.getAuditState()) ? null : articleType.getAuditState().getValue())
                .creatorId(articleType.getCreatorId())
                .description(articleType.getDescription())
                .name(articleType.getName())
                .refCount(articleType.getRefCount())
                .scope(ObjectUtils.isEmpty(articleType.getScope()) ? null : articleType.getScope().getValue())
                .build();

        articleTypeDO.initBase();

        return articleTypeDO;
    }

    /**
     *把 articleTypeDOS内容转移到articleTypes中
     * @param articleTypeDOS
     * @return articleTypes
     */
    public static List<ArticleType> toArticleTypes(List<ArticleTypeDO> articleTypeDOS) {
        List<ArticleType> articleTypes = new ArrayList<>();
        if (ObjectUtils.isEmpty(articleTypeDOS)) {
            return articleTypes;
        }

        articleTypeDOS.forEach(articleTypeDO -> articleTypes.add(toArticleType(articleTypeDO)));

        return articleTypes;
    }

    /**
     * 为articleTypeDO 增加 ID.创建者 创建时间和更新时间
     * @param articleTypeDO
     * @return articleType
     */
    public static ArticleType toArticleType(ArticleTypeDO articleTypeDO) {
        ArticleType articleType = ArticleType.builder()
                .auditState(AuditStateEn.getEntity(articleTypeDO.getAuditState()))
                .creatorId(articleTypeDO.getCreatorId())
                .description(articleTypeDO.getDescription())
                .name(articleTypeDO.getName())
                .refCount(articleTypeDO.getRefCount())
                .scope(ArticleTypeScopeEn.getEntity(articleTypeDO.getScope()))
                .build();
        articleType.setId(articleTypeDO.getId());
        articleType.setCreatorId(articleTypeDO.getCreatorId());
        articleType.setCreateAt(articleTypeDO.getCreateAt());
        articleType.setUpdateAt(articleTypeDO.getUpdateAt());
        return articleType;
    }
}
