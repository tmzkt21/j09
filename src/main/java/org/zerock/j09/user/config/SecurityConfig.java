package org.zerock.j09.user.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerock.j09.user.secyrity.CustomAccessDeniedHandler;
import org.zerock.j09.user.secyrity.filter.ApiCheckFilter;
import org.zerock.j09.user.secyrity.filter.ApiLoginFilter;
import org.zerock.j09.user.secyrity.handler.LoginFailHandler;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("configure.......................");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(apiLoginFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public ApiCheckFilter apiCheckFilter(){
        return new ApiCheckFilter("/api/board/**/*");
    }

    @Bean
    public ApiLoginFilter apiLoginFilter() throws Exception {
        ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/login", authenticationManager());
        //"/login" ?????? ???????????? ????????? ???????????????
        apiLoginFilter.setAuthenticationFailureHandler(new LoginFailHandler());
        return apiLoginFilter;
    }


}


    // ?????? ??????

//        http.authorizeRequests()
//                .antMatchers("/sample/all").permitAll()
//                .antMatchers("/sample/member").hasRole("USER")
//                .antMatchers("/sample/admin").hasRole("ADMIN");
//
//        http.formLogin();
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
//
//
//        http.csrf().disable();


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user00")
//                .password("$2a$10$aepkuLbOsk2bAkWWFKaOSuFmNQuvwQ38W.bcf2.o3vjpdyQlAmdE6" )
//                .authorities("ROLE_USER");
//    }
