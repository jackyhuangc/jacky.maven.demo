/*package com.xujj;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.event.LoggerListener;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

// 此处不建议使用代码的方式，webmvc尽量用配置方式处理，否则不易成功
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	@Bean // 用来bean以后，便可以读取配置中的对应节点信息来使用
	public MyAuthenticationProvider myAuthenticationProvider() {
		MyAuthenticationProvider provider = new MyAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub

		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.permitAll();

		// http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(myAuthenticationProvider());
		// user Details Service验证
		// auth.userDetailsService(userDetailsService()).passwordEncoder(new
		// PasswordEncoder() {
		// // 使用MD5获取加密之后的密码
		// @Override
		// public String encode(CharSequence rawPassword) {
		// return Md5Util.encode((String) rawPassword);
		// }
		//
		// // 验证密码
		// @Override
		// public boolean matches(CharSequence rawPassword, String
		// encodedPassword) {
		// return encodedPassword.equals(Md5Util.encode((String) rawPassword));
		// }
		// });
	}
}*/