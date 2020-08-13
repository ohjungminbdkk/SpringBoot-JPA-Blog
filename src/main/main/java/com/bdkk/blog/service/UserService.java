package com.bdkk.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdkk.blog.model.RoleType;
import com.bdkk.blog.model.User;
import com.bdkk.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	// 트렌젝션 전체가 성공하면 커밋 하나라도 실패하면 fail 롤백
	@Transactional
	public void 회원가입(User user) {
		String rawPassword= user.getPassword(); //1234원문
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
			  userRepository.save(user);
	}

//	@Transactional(readOnly=true) // 셀렉트할때 트랜젝션 시작, 서비스 종료시에 트랙잭션 종료(정합성)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
	
}
