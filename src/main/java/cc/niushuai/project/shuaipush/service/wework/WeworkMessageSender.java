package cc.niushuai.project.shuaipush.service.wework;

import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.common.util.CommonUtil;
import cc.niushuai.project.shuaipush.service.common.sender.MessageSender;
import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;
import cc.niushuai.project.shuaipush.service.wework.api.WeworkApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 企业微信应用消息推送
 *
 * @author niushuai
 * @date 2022/8/5 11:41
 */
@Slf4j
@Component
public class WeworkMessageSender implements MessageSender {

    @Resource
    private WeworkApiService weworkApiService;

    /**
     * 发送消息
     *
     * @param message 发送消息
     * @author niushuai
     * @date: 2022/8/5 11:40
     */
    @Override
    public void send(MessageVO message) {

        if (!CommonUtil.WEWORK_ENABLE) {
            throw new BusinessException("企业微信推送未开启");
        }

        weworkApiService.sendAppMessage(message);

    }
}
