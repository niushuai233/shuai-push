package cc.niushuai.project.shuaipush.service.wechat.api.base;

import cc.niushuai.project.shuaipush.service.common.base.Successful;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 企业微信响应
 *
 * @author niushuai
 * @date 2022/9/1 17:11
 */
@Data
@Accessors(chain = true)
public class WxMpResp implements Successful {

    @JsonProperty("errcode")
    private Long errorCode;

    @JsonProperty("errmsg")
    private String errorMessage;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresIn;

    /**
     * 是否成功
     *
     * @author niushuai
     * @date: 2022/9/1 17:13
     */
    @Override
    public boolean isSuccess() {
        return null == this.getErrorCode() || this.getErrorCode() == 0;
    }
}
