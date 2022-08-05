package cc.niushuai.project.shuaipush.service.wework.api;

import cc.niushuai.project.shuaipush.common.cache.CacheManager;
import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.common.util.RestTemplateUtil;
import cc.niushuai.project.shuaipush.service.common.ApiConstant;
import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;
import cc.niushuai.project.shuaipush.service.wework.api.base.WeworkResp;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 企业微信Api服务
 *
 * @author niushuai
 * @date 2022/8/5 13:44
 */
@Slf4j
@Service
public class WeworkApiService {

    public static final String WEWORK_ACCESS_TOKEN = "wework_access_token";

    /**
     * 获取access_token
     *
     * @author niushuai
     * @date: 2022/8/5 14:37
     * @return: {@link String} access_token
     */
    public String getToken() {

        // 从缓存取
        String accessToken = (String) CacheManager.getDefault().get(WEWORK_ACCESS_TOKEN);
        if (StrUtil.isNotEmpty(accessToken)) {
            return accessToken;
        }

        // 从api取
        WeworkResp weworkResp = RestTemplateUtil.get(ApiConstant.constructWeworkUrl(ApiConstant.Wework.Auth.Get_Token, null), WeworkResp.class);
        if (!weworkResp.isSuccess()) {
            throw new BusinessException("获取Access_Token失败, 错误码:[" + weworkResp.getErrorCode() + "], " + weworkResp.getErrorMessage());
        }
        // 重新 放入缓存
        CacheManager.getDefault().put(WEWORK_ACCESS_TOKEN, weworkResp.getAccessToken(), weworkResp.getExpiresIn() * 1000L);

        return weworkResp.getAccessToken();
    }

    public void sendAppMessage(MessageVO message) {
        String token = getToken();
        System.out.println(token);
    }
}
