package com.ineri.ineri_lk.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MVCConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/styles/**")
                .addResourceLocations("classpath:/static/styles/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/scripts/**")
                .addResourceLocations("classpath:/static/scripts/");
        registry.addResourceHandler("/html/**")
                .addResourceLocations("classpath:/templates/html/");
    }
}
