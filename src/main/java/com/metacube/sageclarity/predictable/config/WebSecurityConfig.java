package com.metacube.sageclarity.predictable.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.metacube.sageclarity.predictable.config.handler.CustomAccessDeniedHandler;
import com.metacube.sageclarity.predictable.config.handler.CustomAuthenticationEntryPoint;
import com.metacube.sageclarity.predictable.config.handler.CustomLoginFailureHandler;
import com.metacube.sageclarity.predictable.config.handler.CustomLoginSuccessfulHandler;
import com.metacube.sageclarity.predictable.config.handler.CustomLogoutSuccessfulHandler;
import com.metacube.sageclarity.predictable.service.securityhelpers.CustomeUserDetailService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


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
      .successHandler(loginSuccessfulHandler)
      .failureHandler(loginFailureHandler)
      //.loginPage("/login.html")
      .defaultSuccessUrl("/")
     // .failureUrl("/login.html?error=true")
      .and()
      .logout();*/

    http
            .httpBasic().and()
            .authorizeRequests()
            .antMatchers("/index.html", "/", "/login" ,
                  /*  "/polyfills.js", "/styles.js" , "/main.js", "/vendor.js" , "/runtime.js",*/
                    "/**.js",
                    "/logout",  "/**.png","/**.jpeg").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

  // @formatter:on
  }
  
  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
}
