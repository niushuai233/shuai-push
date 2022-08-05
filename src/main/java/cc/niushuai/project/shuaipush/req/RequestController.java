package cc.niushuai.project.shuaipush.req;

import cc.niushuai.project.shuaipush.common.base.Result;
import cc.niushuai.project.shuaipush.service.common.sender.MessageSenderFactory;
import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;
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
@RequestMapping
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
    public Result<?> index(@PathVariable("sendKey") String key, @Valid @RequestBody MessageVO message) {

        if (!StrUtil.equals(key, sendKey)) {
            return Result.error("sendKey不匹配, 请检查");
        }

        MessageSenderFactory.get(message.getPlatform()).send(message);

        return Result.success();
    }
}
