package com.bdkk.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bdkk.blog.dto.ResponseDto;
import com.bdkk.blog.model.RoleType;
import com.bdkk.blog.model.User;
import com.bdkk.blog.service.UserService;

//데이터만 리턴해줄거기 때문에
@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	
//	@Autowired
//	private HttpSession session;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
		System.out.println("UserApiController : save 호출된	");
		 userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴(JAcKSON)
	}
	/*
	 * // 다음시간에 스프링 시큐리티 이용해서 로그인!!
	 * 
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession session){
	 * System.out.println("UserApiController : login 호출됨"); 
	 * User principal =userService.로그인(user); if(principal != null) {
	 * session.setAttribute("principal", principal); } return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */
}
