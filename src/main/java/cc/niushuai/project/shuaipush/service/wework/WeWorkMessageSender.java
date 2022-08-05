package cc.niushuai.project.shuaipush.service.wework;

import cc.niushuai.project.shuaipush.service.common.sender.MessageSender;
import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;
import org.springframework.stereotype.Component;

/**
 * 企业微信应用消息推送
 *
 * @author niushuai
 * @date 2022/8/5 11:41
 */
@Component
public class WeWorkMessageSender implements MessageSender {

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
}
