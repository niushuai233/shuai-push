package cc.niushuai.project.shuaipush.service.common.vo;

import cc.niushuai.project.shuaipush.service.common.enums.MessageTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * 接收消息
 *
 * @author niushuai
 * @date 2022/8/5 9:46
 */
@Data
@Accessors(chain = true)
public class MessageVO {

    /**
     * 推送的平台
     */
    @NotBlank(message = "platform 推送平台不能为空")
    private String platform;

    /**
     * 消息类型 默认为text
     *
     * @see
     */
    private String messageType = MessageTypeEnum.Text.getValue();

    /**
     * 消息内容
     */
    @NotBlank(message = "message 消息内容不能为空")
    private String message;

    /**
     * 接收人 默认为@all
     */
    private String toUser = "@all";
}
