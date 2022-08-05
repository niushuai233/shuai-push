package cc.niushuai.project.shuaipush.service.wework.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 应用消息
 *
 * @author niushuai
 * @date: 2022/8/5 18:12
 */
@Data
@Accessors(chain = true)
public class WeworkAppMessage {

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("toparty")
    private String toParty;

    @JsonProperty("totag")
    private String toTag;

    @JsonProperty("msgtype")
    private String msgType;

    @JsonProperty("agentid")
    private Long agentId;

    private Text text;

    private Markdown markdown;

    private Long safe;
    @JsonProperty("enable_id_trans")
    private Long enableIdTrans;
    @JsonProperty("enable_duplicate_check")
    private Long enableDuplicateCheck;
    @JsonProperty("duplicate_check_interval")
    private Long duplicateCheckInterval;

    @Data
    @Accessors(chain = true)
    public static class Text {
        String content;
    }

    @Data
    @Accessors(chain = true)
    public static class Markdown {
        String content;
    }
}