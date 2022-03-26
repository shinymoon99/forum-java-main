package pub.developers.forum.infrastructure.dal.dao;

import org.apache.ibatis.annotations.Param;
import pub.developers.forum.infrastructure.dal.dataobject.ArticleTypeDO;
import java.util.List;

/**文章类型
 * @author Qiangqiang.Bian
 * @create 2020/10/31
 * @desc
 **/
public interface ArticleTypeDAO {
    /**
     * 插入
     * @param articleTypeDO
     */
    void insert(ArticleTypeDO articleTypeDO);

    /**
     * 查询
     * @param articleTypeDO
     * @return
     */
    List<ArticleTypeDO> query(ArticleTypeDO articleTypeDO);

    /**
     * 根据作用范围和状态查询
     * @param scopes
     * @param auditState
     * @return
     */
    List<ArticleTypeDO> queryInScopesAndState(@Param("scopes") List<String> scopes, @Param("auditState") String auditState);

    /**
     * 更新
     * @param articleTypeDO
     */
    void update(ArticleTypeDO articleTypeDO);

    /**
     * 得到文章类型
     * @param id
     * @return
     */
    ArticleTypeDO get(@Param("id") Long id);

    /**
     * 增加引用次数
     * @param id
     */
    void increaseRefCount(@Param("id") Long id);

    /**
     * 减少引用次数
     * @param id
     */
    void decreaseRefCount(@Param("id") Long id);
}
