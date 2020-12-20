package com.jwlee.bootPortfolio.springBoot.config.auth;

import com.jwlee.bootPortfolio.springBoot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면 사용하기 위해 disable
                .and()
                .authorizeRequests()    // URL별 권한 관리를 설정하는 옵션의 시작점 -> 선언되어야 antMatchers 를 사용할수있음
                .antMatchers("/","/css/**","/images/**",    // antMatchers = 권한관리대상을 지정하는 옵션
                        "/js/**","/h2-console/**","/profile").permitAll()  // "/"등 지정된 URL들은 permitAll 옵션을 통해 전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // /api/vi/** 은 USER 권한 가진 사람만 가능하게 옵션
                .anyRequest().authenticated()   // anyRequest -> 이외 모든 요청들을 authenticated -> 모두 인증된 사용자만 사용가능하게 설정
                .and()
                .logout().logoutSuccessUrl("/") // logout후 "/"로 이동
                .and()
                .oauth2Login()
                .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올때의 설정 담당
                .userService(customOAuth2UserService); // 로그인 성공후의 후속조치를 담당 -> 지금은 서비스 구현체를 등록
    }
}
