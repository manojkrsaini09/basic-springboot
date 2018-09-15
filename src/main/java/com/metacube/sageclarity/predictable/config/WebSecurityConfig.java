package com.metacube.sageclarity.predictable.config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacube.sageclarity.predictable.controller.CompanyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.metacube.sageclarity.predictable.config.handler.CustomAccessDeniedHandler;
import com.metacube.sageclarity.predictable.config.handler.CustomAuthenticationEntryPoint;
import com.metacube.sageclarity.predictable.config.handler.CustomLoginFailureHandler;
import com.metacube.sageclarity.predictable.config.handler.CustomLoginSuccessfulHandler;
import com.metacube.sageclarity.predictable.config.handler.CustomLogoutSuccessfulHandler;
import com.metacube.sageclarity.predictable.service.securityhelpers.CustomeUserDetailService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true, securedEnabled = true, jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Autowired
  private CustomLoginSuccessfulHandler loginSuccessfulHandler;

  @Autowired
  private CustomLoginFailureHandler loginFailureHandler;

  @Autowired
  private CustomLogoutSuccessfulHandler logoutSuccessfulHandler;

  @Autowired
  private CustomAccessDeniedHandler customAccessDeniedHandler;

  @Autowired
  private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  @Resource(name = "customeUserDetailService")
  private CustomeUserDetailService userDetailService;

  @Autowired
  private DataSource jdbcDatasource;

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //@formatter:off
      auth.userDetailsService(userDetailService)
        .and().jdbcAuthentication().dataSource(jdbcDatasource);
  // @formatter:on

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

  //@formatter:off
     /* http
      .csrf().disable()
          *//*.formLogin()
              .loginProcessingUrl("/auth/login")
              .defaultSuccessUrl("/")
            //  .successHandler(loginSuccessfulHandler)
             // .failureHandler(loginFailureHandler)
          .and()
              .logout()
              .deleteCookies("JSESSIONID")
              .logoutUrl("/auth/logout")
             // .logoutSuccessHandler(logoutSuccessfulHandler)
            .and()
            .authorizeRequests()
            .antMatchers("/auth/login").permitAll()
            .antMatchers("/secure/admin").access("hasRole('ADMIN')")//.access("hasAuthority('ROLE_ADMIN')")
            .anyRequest().authenticated()
           .and()
             //.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
            // .authenticationEntryPoint(customAuthenticationEntryPoint)
         // .and()
            .anonymous()
              .disable();*//*
      .authorizeRequests()
      .antMatchers("/login*").anonymous()
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/loginForm.html")
              .permitAll()
      .successHandler(loginSuccessfulHandler)
      .failureHandler(loginFailureHandler)
      //.loginPage("/login.html")
      .defaultSuccessUrl("/")
     // .failureUrl("/login.html?error=true")
      .and()
      .logout();*/

    http
            .httpBasic().
            and().formLogin().failureHandler(loginFailureHandler).and()
            .authorizeRequests()
            .antMatchers("/index.html", "/", "/login" ,"/userInfo",
                  /*  "/polyfills.js", "/styles.js" , "/main.js", "/vendor.js" , "/runtime.js",*/
                    "/**.js",
                    "/logout",  "/**.png","/**.jpeg","/resources/**","/assets/**").permitAll()
            .antMatchers("/company/**").permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
            .authenticationEntryPoint(customAuthenticationEntryPoint).and()
            .logout().deleteCookies("JSESSIONID")
            .and()
            .rememberMe().rememberMeParameter("remember-me").key("uniqueAndSecret")
            .tokenValiditySeconds(600)
            .and()
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

  // @formatter:on
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  /*


  private void loginSuccessHandler(
          HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication) throws IOException {

    response.setStatus(HttpStatus.OK.value());
    objectMapper.writeValue(response.getWriter(), "Yayy you logged in!");
  }

  private void loginFailureHandler(
          HttpServletRequest request,
          HttpServletResponse response,
          AuthenticationException e) throws IOException {

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    objectMapper.writeValue(response.getWriter(), "Nopity nop!");
  }

  private void logoutSuccessHandler(
          HttpServletRequest request,
          HttpServletResponse response,
          Authentication authentication) throws IOException {

    response.setStatus(HttpStatus.OK.value());
    objectMapper.writeValue(response.getWriter(), "Bye!");
  }*/
  
}
