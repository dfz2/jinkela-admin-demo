package dev.jinkela.demo.jinkelademo.configuration;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;

import dev.jinkela.demo.jinkelademo.utils.RSAUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableMethodSecurity
class JinkelaSecurityConfiguration {

    @Value("${spring.security.white-list}")
    private String[] whiteList;

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(final PasswordEncoder passwordEncoder, final UserDetailsService userDetailsService) {
        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http, DaoAuthenticationProvider authenticationProvider)
            throws Exception {
        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
        sharedObject.authenticationProvider(authenticationProvider);
        return sharedObject.build();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers(whiteList).permitAll().anyRequest().authenticated());
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler()));
        http.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        http.formLogin(formLogin -> formLogin.successHandler((request,response,authentication)-> {
                    System.out.println("successHandler");
                })
                .failureHandler((request,response,exception)-> {
                    response.setHeader("X-Login-error", "true");
                    response.setStatus(401);
                    response.setCharacterEncoding("utf-8");
                }));

        http.exceptionHandling(exception -> exception.accessDeniedHandler((request,response,accessDeniedException)-> {
            response.setStatus(403);
            response.setCharacterEncoding("utf-8");
        }).authenticationEntryPoint((request,response,authException)-> {
            response.setStatus(401);
            response.setCharacterEncoding("utf-8");
        }));

        http.logout(logout -> logout.deleteCookies("jinkela_distributed_id")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessHandler((request,response,authentication)-> {
                    System.out.println("logoutSuccessHandler");
                })
        );

        // @formatter:on
        return http.build();
    }

    static class CustomAuthenticationProvider extends DaoAuthenticationProvider {
        @Override
        @SuppressWarnings("deprecation")
        protected void additionalAuthenticationChecks(UserDetails userDetails,
                UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
            if (authentication.getCredentials() == null) {
                this.logger.debug("Failed to authenticate since no credentials provided");
                throw new BadCredentialsException(this.messages
                        .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }

            String presentedPassword = authentication.getCredentials().toString();
            ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = Objects.requireNonNull(sra).getRequest();
            HttpSession session = request.getSession();
            String privateKey = (String) session.getAttribute("___private_key___");

            try {
                String decrypt = RSAUtil.decrypt(presentedPassword, privateKey);
                presentedPassword = decrypt;
                // session.removeAttribute("___publicKey___");
            } catch (Exception e) {
                this.logger.debug("Failed to authenticate since password does not match stored value");
                throw new BadCredentialsException(this.messages
                        .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }

            if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
                this.logger.debug("Failed to authenticate since password does not match stored value");
                throw new BadCredentialsException(this.messages
                        .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
    }

    static class CsrfCookieFilter extends OncePerRequestFilter {

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
            csrfToken.getToken();
            filterChain.doFilter(request, response);
        }
    }

    static class SpaCsrfTokenRequestHandler extends CsrfTokenRequestAttributeHandler {
        private final CsrfTokenRequestHandler delegate = new XorCsrfTokenRequestAttributeHandler();

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> csrfToken) {
            this.delegate.handle(request, response, csrfToken);
        }

        @Override
        public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
            if (StringUtils.hasText(request.getHeader(csrfToken.getHeaderName()))) {
                return super.resolveCsrfTokenValue(request, csrfToken);
            }
            return this.delegate.resolveCsrfTokenValue(request, csrfToken);
        }
    }

}
