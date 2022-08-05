package cc.niushuai.project.shuaipush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author niushuai
 */
@EnableConfigurationProperties
@SpringBootApplication
public class ShuaiPushApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShuaiPushApplication.class, args);
	}

}
