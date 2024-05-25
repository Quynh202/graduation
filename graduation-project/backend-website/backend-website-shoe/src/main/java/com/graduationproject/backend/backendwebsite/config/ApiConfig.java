package com.graduationproject.backend.backendwebsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;


@Configuration
public class ApiConfig {

    /**
     * Set template mail.
     *
     * @return bean.
     */
    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean factoryBean() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:/templates/mail");
        return  bean;
    }
}
