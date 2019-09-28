package com.kakaopay.hkp.lgs;

import com.kakaopay.hkp.lgs.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class LocalGovernmentSupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalGovernmentSupportApplication.class, args);
    }

}
