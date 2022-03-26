package pub.developers.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import pub.developers.forum.common.enums.AuditStateEn;
import pub.developers.forum.common.model.PageRequest;
import pub.developers.forum.common.model.PageResult;
import pub.developers.forum.domain.entity.Config;
import pub.developers.forum.domain.repository.ConfigRepository;
import pub.developers.forum.infrastructure.dal.dao.ConfigDAO;
import pub.developers.forum.infrastructure.dal.dataobject.ConfigDO;
import pub.developers.forum.infrastructure.transfer.ConfigTransfer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 配置的相关操作
 * @author Qiangqiang.Bian
 * @create 2020/12/26
 * @desc
 **/
@Repository
public class ConfigRepositoryImpl implements ConfigRepository {

    @Resource
    private ConfigDAO configDAO;

    /**
     * 保存配置
     * @param config
     */
    @Override
    public void save(Config config) {
        ConfigDO configDO = ConfigTransfer.toConfigDO(config);
        configDO.initBase();

        configDAO.insert(configDO);

        config.setId(configDO.getId());
    }

    /**
     * 根据id得到配置
     * @param id
     * @return
     */
    @Override
    public Config get(Long id) {
        return ConfigTransfer.toConfig(configDAO.get(id));
    }

    /**
     * 更新配置
     * @param config
     */
    @Override
    public void update(Config config) {
        configDAO.update(ConfigTransfer.toConfigDO(config));
    }

    /**
     * 查询配置
     * @param types
     * @return
     */
    @Override
    public List<Config> queryAvailable(Set<String> types) {
        return ConfigTransfer.toConfigs(configDAO.queryAvailable(types, new Date(), AuditStateEn.PASS.getValue()));
    }

    /**
     * config分页
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult<Config> page(PageRequest<Config> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        List<ConfigDO> configDOList = configDAO.query(ConfigTransfer.toConfigDO(pageRequest.getFilter()));

        PageInfo<ConfigDO> pageInfo = new PageInfo<>(configDOList);
        if (ObjectUtils.isEmpty(configDOList)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), ConfigTransfer.toConfigs(configDOList));
    }
}
