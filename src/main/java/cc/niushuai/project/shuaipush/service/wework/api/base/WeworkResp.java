package cc.niushuai.project.shuaipush.service.wework.api.base;

import cc.niushuai.project.shuaipush.service.common.base.Successful;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 企业微信接口响应值
 *
 * @author niushuai
 * @date 2022/8/5 15:06
 */
@Data
@Accessors(chain = true)
public class WeworkResp implements Successful {

    @JsonProperty("msgid")
    private String msgId;

    @JsonProperty("errcode")
    private Long errorCode;

    @JsonProperty("errmsg")
    private String errorMessage;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresIn;

    @Override
    public boolean isSuccess() {
        return this.getErrorCode() == 0;
    }
}
