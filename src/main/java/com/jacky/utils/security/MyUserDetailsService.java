package com.jacky.utils.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * ʵ��(implements)UserDetailsService�ӿ�
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:54
 * @since jdk1.8
 */
public class MyUserDetailsService implements UserDetailsService {

	private static Logger logger = Logger.getLogger(MyUserDetailsService.class);

	// @Autowired
	// private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

		logger.debug("UserDetails invoked");
		// AppUser appUser = userDao.findUser(name);
		// return new
		// User(appUser.getName(),appUser.getPassword(),true,true,true,true,appUser.getAuthorities());
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		// ��������û���Ȩ�ޡ�ֻҪ���û�Ȩ����ӵ�authorities �����´󼪡�
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new User("admin", "123456", true, true, true, true, authorities);
	}

}
