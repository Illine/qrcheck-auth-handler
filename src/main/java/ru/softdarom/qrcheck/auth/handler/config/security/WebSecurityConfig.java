package ru.softdarom.qrcheck.auth.handler.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApiKeyAuthorizationConfig.ApiKeyAuthorizationFilter apiKeyAuthorizationFilter;
    private final ApiKeyAuthorizationConfig.KeyApiAuthenticationProvider keyApiAuthenticationProvider;
    private final AuthenticationEntryPoint defaultAuthenticationEntryPoint;

    @Autowired
    WebSecurityConfig(ApiKeyAuthorizationConfig.ApiKeyAuthorizationFilter apiKeyAuthorizationFilter,
                      ApiKeyAuthorizationConfig.KeyApiAuthenticationProvider keyApiAuthenticationProvider,
                      AuthenticationEntryPoint defaultAuthenticationEntryPoint) {
        this.apiKeyAuthorizationFilter = apiKeyAuthorizationFilter;
        this.keyApiAuthenticationProvider = keyApiAuthenticationProvider;
        this.defaultAuthenticationEntryPoint = defaultAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .formLogin()
                    .disable()
                .httpBasic()
                    .disable()
                .csrf()
                    .disable()
                .addFilter(apiKeyAuthorizationFilter)
                .authenticationProvider(keyApiAuthenticationProvider)
                .authorizeRequests(request ->
                        request
                                .antMatchers("/tokens/refresh").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .exceptionHandling(handlerConfigurer -> handlerConfigurer.authenticationEntryPoint(defaultAuthenticationEntryPoint));
        // @formatter:on
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-api/**"
                )
                .antMatchers(
                        "/actuator/health/**",
                        "/actuator/prometheus/**"
                );
    }
}