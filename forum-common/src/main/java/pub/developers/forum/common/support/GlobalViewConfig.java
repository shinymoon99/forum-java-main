package pub.developers.forum.common.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 该类定义了一些全局视图配置信息。
 * @author lixiaoyang
 * @create 2022/3/20
 **/
@Data
@ConfigurationProperties(prefix = "custom-config.view.global")
@Component
public class GlobalViewConfig {

    private String cdnImgStyle;

    private String websiteRecord;

    private Integer pageSize;

    private String websiteName;

    private String websiteLogoUrl;

    private String websiteFaviconIconUrl;

    private String contactMeWxQrCode;

    private String contactMeTitle;

    private String githubClientId;

    private String githubOauthUrl;

    /**
     * 返回一个获取Github授权的链接
     * @return
     */
    public String getGithubOauthUrl() {
        // https://github.com/login/oauth/authorize?client_id=5c00b7f2065217732aa3&scope=user
        return  "https://github.com/login/oauth/authorize?client_id=" + githubClientId + "&scope=user";
    }
}
