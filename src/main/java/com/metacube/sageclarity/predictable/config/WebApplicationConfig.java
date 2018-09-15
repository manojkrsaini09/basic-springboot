package com.metacube.sageclarity.predictable.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.*;
import org.springframework.boot.web.server.*;
import org.springframework.boot.web.embedded.tomcat.*;
import org.springframework.http.*;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

@Override
public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/notFound").setViewName("forward:/index.html");
}


@Bean
public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
    return container -> {
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
                "/notFound"));
        container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,
                "/notFound"));
        container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED,
                "/notFound"));
    };
  }

}