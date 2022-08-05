package cc.niushuai.project.shuaipush.service.common.vo;

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
     * 消息类型
     *
     * @see
     */
    @NotBlank(message = "msgType 消息类型不能为空")
    private String msgType;

    /**
     * 消息内容
     */
    @NotBlank(message = "message 消息内容不能为空")
    private String message;

    /**
     * 接收人 所有人为 @all
     */
    @NotBlank(message = "toUid 接收人不能为空")
    private String toUid;
}
