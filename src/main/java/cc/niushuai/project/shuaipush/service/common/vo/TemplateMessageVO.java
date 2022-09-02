package cc.niushuai.project.shuaipush.service.common.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 模板消息
 *
 * @author niushuai
 * @date 2022/8/31 17:55
 */
@Data
@Accessors(chain = true)
public class TemplateMessageVO {

    /**
     * 推送的平台
     */
    @NotEmpty(message = "推送平台不能为空")
    private String platform;

    @NotEmpty(message = "接收人不能为空")
    private String toUser;

    @NotEmpty(message = "模板消息ID不能为空")
    private String templateId;

    @NotNull(message = "模板消息变量不能为空")
    private List<Template> variables;

    @Data
    @Accessors(chain = true)
    public static class Template {

        @NotEmpty(message = "模板Key不能为空")
        private String key;
        @NotEmpty(message = "模板Value不能为空")
        private String value;
        private String color;
    }
}
