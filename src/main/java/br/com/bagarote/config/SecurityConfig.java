package br.com.bagarote.config;
import br.com.bagarote.security.AuthoritiesExtractorImpl;
import br.com.bagarote.security.CustomJwtAuthenticationConverter;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
				.and()
				.authorizeRequests().antMatchers("/**.html","/**open-api.yml","/api-docs/**","/swagger-ui/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.oauth2ResourceServer()
				.jwt().jwtAuthenticationConverter(getJwtAuthenticationConverter());
	}

	private CustomJwtAuthenticationConverter getJwtAuthenticationConverter() {
		//JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
		CustomJwtAuthenticationConverter authenticationConverter = new CustomJwtAuthenticationConverter();
		//converter.setAuthoritiesClaimName("authorities");
		//converter.setAuthoritiesClaimName("roles");
		//converter.setAuthorityPrefix("");
		//JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
		//authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
		return authenticationConverter;
	}

	@Bean
	public AuthoritiesExtractor authoritiesExtractorImpl(){
		return new AuthoritiesExtractorImpl();
	}

}