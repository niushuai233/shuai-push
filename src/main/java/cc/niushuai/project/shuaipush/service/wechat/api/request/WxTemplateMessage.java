package cc.niushuai.project.shuaipush.service.wechat.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 微信公众号模板消息
 *
 * @author niushuai
 * @date 2022/9/1 17:40
 */
@Data
@Accessors(chain = true)
public class WxTemplateMessage {

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("topcolor")
    private String topColor;

    @JsonProperty("data")
    private Map<String, Item> data;

    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class Item {
        String color;
        String value;
    }
}
