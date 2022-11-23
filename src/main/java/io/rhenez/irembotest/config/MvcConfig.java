package io.rhenez.irembotest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirname="uploads";
        Path userPhotoDir= Paths.get(dirname);
        String userphotospath=userPhotoDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/"+dirname+"/**")
                .addResourceLocations("file:///"+userphotospath+"/");
    }

}
