package cc.niushuai.project.shuaipush.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Slf4j
@Configuration
public class WebSocketConfiguration {

    @Bean
    public ServerEndpointExporter serverEndpointExporter (){
        
        ServerEndpointExporter exporter = new ServerEndpointExporter();
        
        // 手动注册 WebSocket 端点
        log.info("websocket register ExchangeChannel point");
        exporter.setAnnotatedEndpointClasses(ExchangeChannel.class);
        
        return exporter;
    }  
}