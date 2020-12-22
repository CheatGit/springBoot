package net.hicp.localhost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan({
        "net.hicp.localhost.control",
        "net.hicp.localhost.exception",
        "net.hicp.localhost.config",
        "net.hicp.localhost.async",
        "net.hicp.localhost.aop",
})
//@NacosPropertySource(dataId = "example", autoRefreshed = true)
@EnableAsync
@EnableConfigurationProperties
public class App {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }
}
