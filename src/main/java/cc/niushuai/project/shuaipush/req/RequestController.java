package cc.niushuai.project.shuaipush.req;

import cc.niushuai.project.shuaipush.common.base.Result;
import cc.niushuai.project.shuaipush.service.common.vo.MessageVO;
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

    /**
     * 发送消息
     *
     * @param key     发送的key
     * @param message 消息内容
     * @author niushuai
     * @date: 2022/8/5 9:42
     * @return: {@link Result<?>} 响应体
     */
    @PostMapping("/send/{key}")
    public Result<?> index(@PathVariable String key, @Valid @RequestBody MessageVO message) {

        return Result.success();
    }
}
