package pub.developers.forum.app.transfer;

import org.springframework.util.ObjectUtils;
import pub.developers.forum.api.request.user.UserOptLogPageRequest;
import pub.developers.forum.common.enums.OptLogTypeEn;
import pub.developers.forum.domain.entity.OptLog;

/**
 * 登陆页面请求转换为builder的build，序列化
 * @author Qiangqiang.Bian
 * @create 2020/12/9
 * @desc
 * 登陆页面请求转换为builder的build，序列化
 **/
public class OptLogTransfer {

    public static OptLog toOptLog(UserOptLogPageRequest request) {
        return OptLog.builder()
                .operatorId(request.getOperatorId())
                .type(ObjectUtils.isEmpty(request.getType()) ? null : OptLogTypeEn.getEntity(request.getType()))
                .build();
    }

}
