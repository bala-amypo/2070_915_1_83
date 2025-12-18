@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/auth/**", "/swagger-ui/**").permitAll()
            .anyRequest().authenticated();
        return http.build();
    }
}
