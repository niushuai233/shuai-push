package cc.niushuai.project.shuaipush.service.common.sender;

import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;

/**
 * 发送消息接口
 *
 * @author niushuai
 * @date 2022/8/5 11:39
 */
public interface MessageSender {


    /**
     * 发送消息
     *
     * @param message 发送消息
     * @author niushuai
     * @date: 2022/8/5 11:40
     */
    void send(MessageVO message);
}
