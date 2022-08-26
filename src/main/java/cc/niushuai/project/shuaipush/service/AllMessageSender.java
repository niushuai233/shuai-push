package cc.niushuai.project.shuaipush.service;

import cc.niushuai.project.shuaipush.service.common.sender.MessageSender;
import cc.niushuai.project.shuaipush.service.common.sender.MessageSenderFactory;
import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 所有消息来一遍
 *
 * @author niushuai
 * @date 2022/8/26 10:18
 */
@Slf4j
@Component
public class AllMessageSender implements MessageSender {

    /**
     * 发送消息
     *
     * @param message 发送消息
     * @author niushuai
     * @date: 2022/8/5 11:40
     */
    @Override
    public void send(MessageVO message) {
        MessageSenderFactory.senders().values()
                .stream().filter(item -> !item.getClass().getCanonicalName().equals(this.getClass().getCanonicalName()))
                .forEach(item -> {
                    try {
                        item.send(message);
                    } catch (Exception e) {
                        log.error("发送失败: {}, {}", item.getClass().getCanonicalName(), e.getMessage(), e);
                    }
                });
    }
}
