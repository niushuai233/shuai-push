package cc.niushuai.project.shuaipush.common.config;

import cn.hutool.core.io.IoUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequestDetails(request);
        ClientHttpResponse httpResponse = execution.execute(request, body);
        logResponseDetails(httpResponse);
        return httpResponse;
    }

    private void logRequestDetails(HttpRequest request) {
        log.debug("Headers: {}", request.getHeaders());
        log.debug("Request Method: {}", request.getMethod());
        log.debug("Request URI: {}", request.getURI());
    }

    @SneakyThrows
    private void logResponseDetails(ClientHttpResponse response) {
        log.debug("Status Code: {}", response.getRawStatusCode());
        log.debug("Status Text: {}", response.getStatusText());
        log.debug("Response Body: {}", IoUtil.readUtf8(response.getBody()));
    }

}
