package pub.developers.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import pub.developers.forum.common.enums.ArticleTypeScopeEn;
import pub.developers.forum.common.enums.AuditStateEn;
import pub.developers.forum.common.enums.ErrorCodeEn;
import pub.developers.forum.common.exception.BizException;
import pub.developers.forum.common.model.PageRequest;
import pub.developers.forum.common.model.PageResult;
import pub.developers.forum.domain.entity.ArticleType;
import pub.developers.forum.domain.repository.ArticleTypeRepository;
import pub.developers.forum.infrastructure.dal.dao.ArticleTypeDAO;
import pub.developers.forum.infrastructure.dal.dataobject.ArticleTypeDO;
import pub.developers.forum.infrastructure.transfer.ArticleTypeTransfer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文章类型的操作，查询
 * @author Qiangqiang.Bian
 * @create 2020/10/31
 * @desc
 **/
@Repository
public class ArticleTypeRepositoryImpl implements ArticleTypeRepository {

    @Resource
    private ArticleTypeDAO articleTypeDAO;

    /**
     *保存文章类型
     * @param articleType
     */
    @Override
    public void save(ArticleType articleType) {
        ArticleTypeDO articleTypeDO = ArticleTypeTransfer.toArticleTypeDO(articleType);
        try {
            articleTypeDAO.insert(articleTypeDO);
            articleType.setId(articleTypeDO.getId());
        } catch (DuplicateKeyException e) {
            throw new BizException(ErrorCodeEn.ARTICLE_TYPE_IS_EXIST);
        }
    }

    /**
     * 查询文件类型
     * @param articleType
     * @return
     */
    @Override
    public List<ArticleType> query(ArticleType articleType) {
        return ArticleTypeTransfer.toArticleTypes(articleTypeDAO.query(ArticleTypeTransfer.toArticleTypeDO(articleType)));
    }

    /**
     *根据状态查询文章类型
     * @param auditState
     * @return
     */
    @Override
    public List<ArticleType> queryByState(AuditStateEn auditState) {
        List<ArticleTypeDO> articleTypeDOS = articleTypeDAO.query(ArticleTypeDO.builder()
                .auditState(ObjectUtils.isEmpty(auditState) ? null : auditState.getValue())
                .build());

        return ArticleTypeTransfer.toArticleTypes(articleTypeDOS);
    }

    /**
     *根据作用范围和状态查询文章类型
     * @param scopes
     * @param auditState
     * @return
     */
    @Override
    public List<ArticleType> queryByScopesAndState(List<ArticleTypeScopeEn> scopes, AuditStateEn auditState) {
        List<ArticleTypeDO> articleTypeDOS = articleTypeDAO.queryInScopesAndState(scopes.stream()
                .map(ArticleTypeScopeEn::getValue)
                .collect(Collectors.toList()), auditState.getValue());

        return ArticleTypeTransfer.toArticleTypes(articleTypeDOS);
    }

    /**
     * 更新文章类型
     * @param articleType
     */
    @Override
    public void update(ArticleType articleType) {
        ArticleTypeDO articleTypeDO = ArticleTypeDO.builder().build();
        articleTypeDO.setId(articleType.getId());
        if (!ObjectUtils.isEmpty(articleType.getAuditState())) {
            articleTypeDO.setAuditState(articleType.getAuditState().getValue());
        }

        articleTypeDAO.update(articleTypeDO);
    }

    /**
     * 根据id找到文章类型
     * @param id
     * @return
     */
    @Override
    public ArticleType get(Long id) {
        return ArticleTypeTransfer.toArticleType(articleTypeDAO.get(id));
    }

    /**
     * 根据NameAndState找到文章类型
     * @param typeName
     * @param auditState
     * @return
     */
    @Override
    public ArticleType getByNameAndState(String typeName, AuditStateEn auditState) {
        List<ArticleTypeDO> articleTypeDOS = articleTypeDAO.query(ArticleTypeDO.builder()
                .name(typeName)
                .auditState(auditState.getValue())
                .build());

        if (ObjectUtils.isEmpty(articleTypeDOS)) {
            return null;
        }

        return ArticleTypeTransfer.toArticleType(articleTypeDOS.get(0));
    }

    /**
     * 增加引用次数
     * @param id
     */
    @Override
    public void increaseRefCount(Long id) {
        articleTypeDAO.increaseRefCount(id);
    }

    /**
     * 减少引用次数
     * @param id
     */
    @Override
    public void decreaseRefCount(Long id) {
        articleTypeDAO.decreaseRefCount(id);
    }

    /**
     * 文章类型分页
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult<ArticleType> page(PageRequest<ArticleType> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        ArticleType articleType = pageRequest.getFilter();
        ArticleTypeDO articleTypeDO = ArticleTypeDO.builder()
                .name(articleType.getName())
                .description(articleType.getDescription())
                .auditState(ObjectUtils.isEmpty(articleType.getAuditState()) ? null : articleType.getAuditState().getValue())
                .scope(ObjectUtils.isEmpty(articleType.getScope()) ? null : articleType.getScope().getValue())
                .build();
        List<ArticleTypeDO> articleTypeDOList = articleTypeDAO.query(articleTypeDO);
        PageInfo<ArticleTypeDO> pageInfo = new PageInfo<>(articleTypeDOList);

        if (ObjectUtils.isEmpty(articleTypeDOList)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), ArticleTypeTransfer.toArticleTypes(articleTypeDOList));
    }
}
