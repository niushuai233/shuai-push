package cc.niushuai.project.shuaipush.service.wechat;

import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.common.util.CommonUtil;
import cc.niushuai.project.shuaipush.service.common.sender.MessageSender;
import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;
import cc.niushuai.project.shuaipush.service.common.vo.TemplateMessageVO;
import cc.niushuai.project.shuaipush.service.wechat.api.WxApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 微信公众号消息
 *
 * @author niushuai
 * @date 2022/9/1 15:25
 */
@Slf4j
@Component
public class WxMessageSender implements MessageSender {

    @Resource
    private WxApiService wxApiService;

    /**
     * 发送消息
     *
     * @param message 发送消息
     * @author niushuai
     * @date: 2022/8/5 11:40
     */
    @Override
    public void send(MessageVO message) {

    }

    @Override
    public void templateSend(TemplateMessageVO templateMessage) {
        if (!CommonUtil.WEIXIN_ENABLE) {
            throw new BusinessException("微信公众号推送未开启");
        }

        wxApiService.sendTemplateMessage(templateMessage);
    }
}
