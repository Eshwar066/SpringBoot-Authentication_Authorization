package com.example.demo_SpringSecurity.configration;

import com.example.demo_SpringSecurity.utils.RSAKeyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.example.demo_SpringSecurity.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class SecurityConfiguration {
	
	private final RSAKeyProperties  keys;

	 public SecurityConfiguration (RSAKeyProperties keys) {
		 this.keys=keys;
	 }

	@Bean                                                     //optional
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		String idForEncode = "bcrypt";
//		Map<String, PasswordEncoder> encoderMap = new HashMap<>();
//		encoderMap.put(idForEncode, new BCryptPasswordEncoder());
//		return new DelegatingPasswordEncoder(idForEncode, encoderMap);
//	}

	@Bean 
	public AuthenticationManager authManager(UserDetailsService detailsService) {
		DaoAuthenticationProvider daoProvider=new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(detailsService);
		daoProvider.setPasswordEncoder(passwordEncoder());                         //optional
		return new ProviderManager(daoProvider);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		return http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth->{
					auth.requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll();
					auth.anyRequest().authenticated();
				})
//				.httpBasic().and()
				.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
	}

	@Bean
	public JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
		JWKSource<SecurityContext> jwks=new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

//	@Bean
//	public JwtAuthenticationConverter jwtAuthenticationConverter(){
//		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter=new JwtGrantedAuthoritiesConverter();
//		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("rles");
//		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("Role_");
//		JwtAuthenticationConverter jwtConverter=new JwtAuthenticationConverter();
//		jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//		return jwtConverter;
//	}
}
