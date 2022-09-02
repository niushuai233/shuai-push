package cc.niushuai.project.shuaipush.service.common.sender;

import cc.niushuai.project.shuaipush.common.base.BaseEnum;
import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.service.AllMessageSender;
import cc.niushuai.project.shuaipush.service.common.enums.PlatformEnum;
import cc.niushuai.project.shuaipush.service.wechat.WxMessageSender;
import cc.niushuai.project.shuaipush.service.wework.WeworkMessageSender;
import cn.hutool.extra.spring.SpringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 消息发送者工厂类
 *
 * @author niushuai
 * @date 2022/8/5 11:47
 */
@Slf4j
public class MessageSenderFactory {

    private static final Map<PlatformEnum, MessageSender> senders = new HashMap<>();

    static {
        put(PlatformEnum.Weixin, WxMessageSender.class);
        put(PlatformEnum.Wework, WeworkMessageSender.class);
        put(PlatformEnum.ALL, AllMessageSender.class);
    }

    public static Map<PlatformEnum, MessageSender> senders() {
        return senders;
    }

    /**
     * 加载推送平台实例
     *
     * @param platformEnum       推送平台
     * @param messageSenderClass 实例class
     * @author niushuai
     * @date: 2022/8/5 11:58
     */
    private static void put(PlatformEnum platformEnum, Class<? extends MessageSender> messageSenderClass) {
        log.info("加载推送平台实例: {} --> {}", platformEnum.getDesc(), messageSenderClass);
        senders.put(platformEnum, SpringUtil.getBean(messageSenderClass));
    }

    /**
     * 根据推送平台的不同取出相应平台发送消息的bean
     *
     * @param platform 推送平台
     * @author niushuai
     * @date: 2022/8/5 11:50
     * @return: {@link MessageSender}
     */
    public static MessageSender get(String platform) {

        PlatformEnum platformEnum = Optional.ofNullable(BaseEnum.matchEnum(platform, PlatformEnum.class))
                .orElseThrow(() -> new BusinessException("当前平台[" + platform + "]不支持, 请联系作者."));

        return senders.get(platformEnum);
    }
}
