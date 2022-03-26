package pub.developers.forum.common.support;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.util.ObjectUtils;

/**
 * 该工具类用于头像显示.
 * @author lixiaoyang
 * @create 2022/3/20
 **/
public class AvatarUtil {

    // https://www.gravatar.com/avatar/
    private static final String GRAVATAR_URL = "https://sdn.geekzu.org/avatar/%s?d=retro";

    /**
     * 返回一个查找用户头像的链接.
     * @param avatar Gravatar网站链接
     * @param email 用户邮箱
     * @return 用户头像的链接地址
     */
    public static String get(String avatar, String email) {
        return ObjectUtils.isEmpty(avatar) ? String.format(GRAVATAR_URL, DigestUtils.md5Hex(ObjectUtils.isEmpty(email) ? "" : email)) : avatar;
    }

}
