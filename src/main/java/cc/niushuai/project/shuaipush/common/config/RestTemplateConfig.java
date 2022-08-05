package cc.niushuai.project.shuaipush.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

/**
 * RestTemplate Config
 *
 * @author niushuai
 * @date 2022/8/5 14:43
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 单位为ms
        factory.setReadTimeout(30000);
        // 单位为ms
        factory.setConnectTimeout(30000);
        return factory;
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
        // 替换原有的字符串解析为utf8编码
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        Iterator<HttpMessageConverter<?>> iterator = messageConverters.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof StringHttpMessageConverter) {
                iterator.remove();
            }
        }

        messageConverters.add(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
