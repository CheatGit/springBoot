package net.hicp.localhost.config.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author cheat
 * @updateTime 2020-12-22 22:40
 * @desc
 */
@ConfigurationProperties(prefix = "bxh.info",ignoreUnknownFields=false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
/**
 * 验证
 */
@Validated
public class UserProperties {
    /**
     * 验证
     */
    @Min(20)
    @Max(30)
    private Integer age;
    private Integer score;
    private String weight;
    private String heigth;
}
