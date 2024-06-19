package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

        @Bean
        public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
            serverHttpSecurity
                    .csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .authorizeExchange(exchange ->
                            exchange.pathMatchers("/eureka/**")
                                    .permitAll()
                                    .anyExchange()
                                    .authenticated())
                    .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));
            return serverHttpSecurity.build();
        }
//        @Bean
//        public ReactiveAuthenticationManager authenticationManager() {
//            return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService());
//        }
//
//        @Bean
//        public ReactiveUserDetailsService userDetailsService() {
//            UserDetails user = User.withDefaultPasswordEncoder()
//                    .username("user")
//                    .password("password")
//                    .roles("USER")
//                    .build();
//            return new MapReactiveUserDetailsService(user);
//        }
//        @Bean
//        public ReactiveJwtDecoder jwtDecoder() {
//            return NimbusReactiveJwtDecoder.withJwkSetUri("https://your-auth-server.com/.well-known/jwks.json").build();
//        }
    }

