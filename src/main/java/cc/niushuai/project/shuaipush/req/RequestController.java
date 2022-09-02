package cc.niushuai.project.shuaipush.req;

import cc.niushuai.project.shuaipush.common.base.Result;
import cc.niushuai.project.shuaipush.common.exception.BusinessException;
import cc.niushuai.project.shuaipush.service.common.enums.MessageTypeEnum;
import cc.niushuai.project.shuaipush.service.common.enums.PlatformEnum;
import cc.niushuai.project.shuaipush.service.common.sender.MessageSenderFactory;
import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;
import cc.niushuai.project.shuaipush.service.common.vo.TemplateMessageVO;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 请求统一入口
 *
 * @author niushuai
 * @date 2022/8/4 17:53
 */
@RestController
@RequestMapping("/")
public class RequestController {

    @Value("${push.sendKey:}")
    private String sendKey;

    /**
     * 发送消息
     *
     * @param key     发送的key
     * @param message 消息内容
     * @author niushuai
     * @date: 2022/8/5 9:42
     * @return: {@link Result<?>} 响应体
     */
    @PostMapping("/send/{sendKey}")
    public Result<?> send(@PathVariable("sendKey") String key, @Valid @RequestBody MessageVO message) {

        this.verifySendKey(key);

        String platform = message.getPlatform();
        if (StrUtil.isEmpty(platform)) {
            // 不传就全部来一遍
            platform = PlatformEnum.ALL.getValue();
        }
        if (StrUtil.isEmpty(message.getMessageType())) {
            message.setMessageType(MessageTypeEnum.Text.getValue());
        }
        MessageSenderFactory.get(platform).send(message);

        return Result.success();
    }

    /**
     * 发送模板消息
     *
     * @param templateMessage 模板消息
     * @author niushuai
     * @date: 2022/8/31 17:54
     * @return: {@link Result<?>}
     */
    @PostMapping("/templateSend/{sendKey}")
    public Result<?> templateSend(@PathVariable("sendKey") String key, @Valid @RequestBody TemplateMessageVO templateMessage) {

        MessageSenderFactory.get(templateMessage.getPlatform()).templateSend(templateMessage);

        return Result.success();
    }

    /**
     * 校验key是否一致
     *
     * @param key sendKey
     * @author niushuai
     * @date: 2022/8/31 17:43
     */
    private void verifySendKey(String key) {
        if (StrUtil.equals(key, sendKey)) {
            return;
        }
        throw new BusinessException("sendKey[{}]不匹配, 请检查", key);
    }
}
