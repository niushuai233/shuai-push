package cc.niushuai.project.shuaipush.service.common.config;

import cc.niushuai.project.shuaipush.common.util.CommonUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 推送配置
 *
 * @author niushuai
 * @date 2022/8/5 13:44
 */
@Data
@Accessors(chain = true)
@Configuration
@ConfigurationProperties(prefix = "push")
public class PushConfig {

    private String sendKey;

    private WeworkConfig wework;

    @PostConstruct
    public void init() {
        this.verify();
        System.out.println("配置文件加载成功");
    }

    public void verify() {
        CommonUtil.nullable(this.getSendKey(), "请配置SendKey");
        this.wework.verify();
    }
}
