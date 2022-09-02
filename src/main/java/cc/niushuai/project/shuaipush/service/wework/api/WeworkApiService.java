package cc.niushuai.project.shuaipush.service.wework.api;

import cc.niushuai.project.shuaipush.common.base.BaseEnum;
import cc.niushuai.project.shuaipush.common.cache.CacheManager;
import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.common.util.CommonUtil;
import cc.niushuai.project.shuaipush.common.util.RestTemplateUtil;
import cc.niushuai.project.shuaipush.service.common.ApiConstant;
import cc.niushuai.project.shuaipush.service.common.ApiService;
import cc.niushuai.project.shuaipush.service.common.enums.MessageTypeEnum;
import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;
import cc.niushuai.project.shuaipush.service.wework.api.base.WeworkResp;
import cc.niushuai.project.shuaipush.service.wework.api.request.WeworkAppMessage;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static cc.niushuai.project.shuaipush.service.common.ApiConstant.Wework.Keys;
import static cc.niushuai.project.shuaipush.service.common.ApiConstant.Wework.Message;


/**
 * 企业微信Api服务
 *
 * @author niushuai
 * @date 2022/8/5 13:44
 */
@Slf4j
@Service
public class WeworkApiService implements ApiService {

    /**
     * 获取access_token 各个服务token是必须的
     *
     * @author niushuai
     * @date: 2022/8/5 14:37
     * @return: {@link String} access_token
     */
    @Override
    public String getAccessToken() {

        // 从缓存取
        String accessToken = (String) CacheManager.getDefault().get(Keys.ACCESS_TOKEN);
        if (StrUtil.isNotEmpty(accessToken)) {
            return accessToken;
        }

        // 从api取
        WeworkResp weworkResp = RestTemplateUtil.get(ApiConstant.constructWeworkUrl(ApiConstant.Wework.Auth.Get_Token, null, getWework()), WeworkResp.class);
        parseResponse("获取Access_Token失败", weworkResp);
        // 重新 放入缓存
        CacheManager.getDefault().put(Keys.ACCESS_TOKEN, weworkResp.getAccessToken(), weworkResp.getExpiresIn() * 1000L);

        return weworkResp.getAccessToken();
    }


    public void sendAppMessage(MessageVO message) {

        WeworkAppMessage weworkAppMessage = new WeworkAppMessage()
                .setMsgType(message.getMessageType())
                .setAgentId(getWework().getAppId())
                .setSafe(0L)
                .setEnableIdTrans(0L)
                .setEnableDuplicateCheck(0L)
                .setDuplicateCheckInterval(1800L);

        // 没传就@all
        if (StrUtil.isEmpty(message.getToUser())) {
            weworkAppMessage.setToUser(Keys.ALL_USER);
        } else {
            weworkAppMessage.setToUser(message.getToUser());
        }

        MessageTypeEnum messageTypeEnum = BaseEnum.matchEnum(message.getMessageType(), MessageTypeEnum.class);
        CommonUtil.nullable(messageTypeEnum, "未知的推送消息类型, 仅支持text与markdown类型");

        // 设置不同的消息内容
        switch (messageTypeEnum) {
            case Text:
                weworkAppMessage.setText(new WeworkAppMessage.Text().setContent(message.getMessage()));
                break;
            case Markdown:
                weworkAppMessage.setMarkdown(new WeworkAppMessage.Markdown().setContent(message.getMessage()));
                break;
            default:
        }

        WeworkResp weworkResp = RestTemplateUtil.postJson(ApiConstant.constructWeworkUrl(Message.Send, this.getAccessToken(), getWework()), weworkAppMessage, WeworkResp.class);
        parseResponse("企业微信应用消息推送失败", weworkResp);
    }

    private void parseResponse(String title, WeworkResp weworkResp) {
        if (!weworkResp.isSuccess()) {
            throw new BusinessException(title + ", 错误码:[" + weworkResp.getErrorCode() + "], " + weworkResp.getErrorMessage());
        }
    }
}
