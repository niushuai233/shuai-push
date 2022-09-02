package cc.niushuai.project.shuaipush.service.wechat.api;

import cc.niushuai.project.shuaipush.common.cache.CacheManager;
import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.common.util.RestTemplateUtil;
import cc.niushuai.project.shuaipush.service.common.ApiConstant;
import cc.niushuai.project.shuaipush.service.common.ApiService;
import cc.niushuai.project.shuaipush.service.common.vo.TemplateMessageVO;
import cc.niushuai.project.shuaipush.service.wechat.api.base.WxMpResp;
import cc.niushuai.project.shuaipush.service.wechat.api.request.WxTemplateMessage;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信公众号操作
 *
 * @author niushuai
 * @date 2022/9/1 15:27
 */
@Service
public class WxApiService implements ApiService {

    /**
     * 发送模板消息
     *
     * @param templateMessage 模板消息所需要的参数
     * @author niushuai
     * @date: 2022/9/1 16:55
     */
    public void sendTemplateMessage(TemplateMessageVO templateMessage) {

        WxTemplateMessage request = new WxTemplateMessage();

        List<TemplateMessageVO.Template> variables = templateMessage.getVariables();
        Map<String, WxTemplateMessage.Item> data = new HashMap<>(variables.size());

        for (TemplateMessageVO.Template variable : variables) {
            data.put(variable.getKey(), new WxTemplateMessage.Item(variable.getColor(), variable.getValue()));
        }

        request.setToUser(templateMessage.getToUser())
                .setTemplateId(templateMessage.getTemplateId())
                .setTopColor("#000000")
                .setData(data);

        WxMpResp wxMpResp = RestTemplateUtil.postJson(ApiConstant.constructWeixinUrl(ApiConstant.Weixin.Message.Template_Send, this.getAccessToken(), getWeixin()), request, WxMpResp.class);

        dealResponse("微信公众号发送模板消息失败", wxMpResp);
    }


    @Override
    public String getAccessToken() {

        // 从缓存取
        String accessToken = (String) CacheManager.getDefault().get(ApiConstant.Weixin.Keys.ACCESS_TOKEN);
        if (StrUtil.isNotEmpty(accessToken)) {
            return accessToken;
        }

        // 从api取
        WxMpResp wxMpResp = RestTemplateUtil.get(ApiConstant.constructWeixinUrl(ApiConstant.Weixin.Auth.Get_Token, null, getWeixin()), WxMpResp.class);
        dealResponse("微信公众号获取AccessToken失败", wxMpResp);

        // 重新 放入缓存
        CacheManager.getDefault().put(ApiConstant.Weixin.Keys.ACCESS_TOKEN, wxMpResp.getAccessToken(), wxMpResp.getExpiresIn() * 1000L);

        return wxMpResp.getAccessToken();

    }
}
