package com.teak.core.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/26
 */
@ConfigurationProperties(prefix = "spring.mail")
@Data
public class EmailPogo {

    private String sendfrom;

    public EmailPogo() {
    }

    public EmailPogo(String sendfrom) {
        this.sendfrom = sendfrom;
    }
}
