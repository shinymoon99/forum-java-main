package pub.developers.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Sets;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import pub.developers.forum.common.enums.AuditStateEn;
import pub.developers.forum.common.enums.ErrorCodeEn;
import pub.developers.forum.common.enums.PostsCategoryEn;
import pub.developers.forum.common.exception.BizException;
import pub.developers.forum.common.model.PageRequest;
import pub.developers.forum.common.model.PageResult;
import pub.developers.forum.common.support.SafesUtil;
import pub.developers.forum.domain.entity.Posts;
import pub.developers.forum.domain.entity.Tag;
import pub.developers.forum.domain.repository.TagRepository;
import pub.developers.forum.infrastructure.dal.dao.*;
import pub.developers.forum.infrastructure.dal.dataobject.TagDO;
import pub.developers.forum.infrastructure.dal.dataobject.TagPostsMappingDO;
import pub.developers.forum.infrastructure.transfer.TagTransfer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 标签的相关操作
 * @author Qiangqiang.Bian
 * @create 2020/8/3
 * @desc
 **/
@Repository
public class TagRepositoryImpl extends AbstractPostsRepository implements TagRepository {

    @Resource
    private ArticleTypeDAO articleTypeDAO;

    /**
     * 保存标签
     * @param tag
     */
    @Override
    public void save(Tag tag) {
        TagDO tagDO = TagTransfer.toTagDO(tag);

        try {
            tagDAO.insert(tagDO);
            tag.setId(tagDO.getId());
        } catch (DuplicateKeyException e) {
            throw new BizException(ErrorCodeEn.TAG_IS_EXIST);
        }
    }

    /**
     * 查询标签
     * @param tag
     * @return
     */
    @Override
    public List<Tag> query(Tag tag) {
        return TagTransfer.toTags(tagDAO.query(TagTransfer.toTagDO(tag)));
    }

    /**
     * 得到标签ID
     * @param id
     * @return
     */
    @Override
    public Tag get(Long id) {
        return TagTransfer.toTag(tagDAO.get(id));
    }

    /**
     * 更新
     * @param tag
     */
    @Override
    public void update(Tag tag) {
        TagDO tagDO = TagDO.builder()
                .description(tag.getDescription())
                .groupName(tag.getGroupName())
                .name(tag.getName())
                .auditState(ObjectUtils.isEmpty(tag.getAuditState()) ? null : tag.getAuditState().getValue())
                .creatorId(tag.getCreatorId())
                .refCount(tag.getRefCount())
                .build();
        tagDO.setId(tag.getId());

        tagDAO.update(tagDO);
    }

    /**
     * 根据ID查询标签
     * @param ids
     * @return
     */
    @Override
    public List<Tag> queryByIds(Set<Long> ids) {
        return TagTransfer.toTags(tagDAO.queryInIds(ids));
    }

    /**
     * 根据状态查询标签
     * @param auditState
     * @return
     */
    @Override
    public List<Tag> queryByState(AuditStateEn auditState) {
        return TagTransfer.toTags(tagDAO.query(TagDO.builder()
                .auditState(auditState.getValue())
                .build()));
    }

    /**
     * 删除
     * @param articleId
     */
    @Override
    public void deletePostsMapping(Long articleId) {
        List<TagPostsMappingDO> tagPostsMappingDOS = tagPostsMappingDAO.queryInPostsIds(Sets.newHashSet(articleId));
        Set<Long> tagIds = SafesUtil.ofList(tagPostsMappingDOS).stream().map(TagPostsMappingDO::getTagId).collect(Collectors.toSet());
        if (!ObjectUtils.isEmpty(tagIds)) {
            tagDAO.decreaseRefCount(tagIds);
        }
        tagPostsMappingDAO.deleteByPostsId(articleId);
    }

    /**
     * 增加引用次数
     * @param ids
     */
    @Override
    public void increaseRefCount(Set<Long> ids) {
        tagDAO.increaseRefCount(ids);
    }

    /**
     * 减少引用次数
     * @param ids
     */
    @Override
    public void decreaseRefCount(Set<Long> ids) {
        tagDAO.decreaseRefCount(ids);
    }

    /**
     * 根据内容和状态得到标签
     * @param name
     * @param pass
     * @return
     */
    @Override
    public Tag getByNameAndState(String name, AuditStateEn pass) {
        List<TagDO> tagDOS = tagDAO.query(TagDO.builder()
                .auditState(pass.getValue())
                .name(name)
                .build());
        if (ObjectUtils.isEmpty(tagDOS)) {
            return null;
        }

        return TagTransfer.toTag(tagDOS.get(0));
    }

    /**
     *分页
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult<Posts> pagePosts(PageRequest<Long> pageRequest) {
        PageRequest<Set<Long>> pageRequest1 = new PageRequest<>();
        pageRequest1.setPageNo(pageRequest.getPageNo());
        pageRequest1.setPageSize(pageRequest.getPageSize());
        pageRequest1.setFilter(Sets.newHashSet(pageRequest.getFilter()));
        return pagePostsByTagIds(pageRequest1);
    }

    /**
     * 根据Tagids分页
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult<Posts> pagePostsByTagIds(PageRequest<Set<Long>> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());
        List<TagPostsMappingDO> tagPostsMappingDOS = tagPostsMappingDAO.queryInTagIds(pageRequest.getFilter());
        PageInfo<TagPostsMappingDO> pageInfo = new PageInfo<>(tagPostsMappingDOS);

        if (ObjectUtils.isEmpty(tagPostsMappingDOS)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        Set<Long> postsIds = new HashSet<>();
        tagPostsMappingDOS.forEach(tagPostsMappingDO -> postsIds.add(tagPostsMappingDO.getPostsId()));

        return basePagePosts(new ArrayList<>(postsIds), pageInfo, AuditStateEn.PASS);
    }

    /**
     * 根据标签分页
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult<Tag> page(PageRequest<Tag> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        Tag tag = pageRequest.getFilter();
        List<TagDO> tagDOList = tagDAO.query(TagDO.builder()
                .auditState(ObjectUtils.isEmpty(tag.getAuditState()) ? null : tag.getAuditState().getValue())
                .name(tag.getName())
                .groupName(tag.getGroupName())
                .description(tag.getDescription())
                .build());

        PageInfo<TagDO> pageInfo = new PageInfo<>(tagDOList);
        if (ObjectUtils.isEmpty(tagDOList)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), TagTransfer.toTags(tagDOList));
    }
}
