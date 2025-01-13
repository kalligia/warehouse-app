package gr.giatzi.warehouseapp.authentication;

import gr.giatzi.warehouseapp.core.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "index.html").authenticated()
                        .requestMatchers("/warehouse/products").authenticated()
                        .requestMatchers("/warehouse/products/**").authenticated()
                        .requestMatchers("/warehouse/employees").authenticated()
                        .requestMatchers("/warehouse/employees/add").hasAnyAuthority(Role.MANAGER.name(), Role.ADMIN.name())
                        .requestMatchers("/warehouse/employees/edit/**").hasAnyAuthority(Role.MANAGER.name(), Role.ADMIN.name())
                        .requestMatchers("/warehouse/employees/delete/**").hasAnyAuthority(Role.MANAGER.name(), Role.ADMIN.name())
                        .requestMatchers("/warehouse/users/register").permitAll()
                        .requestMatchers("/warehouse/users").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/warehouse/users/delete/**").hasAnyAuthority(Role.ADMIN.name())
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .requestMatchers("/uploads/**").authenticated()
                        .anyRequest().authenticated()

              )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/")
                )
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(e -> e
                        .accessDeniedPage("/access-denied")
                )  ;
        return http.build();
    }
}
