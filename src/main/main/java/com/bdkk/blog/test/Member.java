package com.bdkk.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// @Getter
// @Setter
// @Data // getter setter 전부 포함 어노테이션
//  @AllArgsConstructor // 생성자 생성시 어노테이션
// @RequiredArgsConstructor // final이 붙은 변수에 대한 생성자를 만들어주는 어노테이션

@Data
//@AllArgsConstructor // 전체 생성자
@NoArgsConstructor // 빈생성자
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
	// @AllArgsConstructor 똑같은거
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	// final 로 지정시, 불변성이 없게 만들기 위함
	// 변경을 원하는 거라면 final 지정 하지않는다.
//	private final int id;
//	private final String username;
//	private final String password;
//	private final String email;

	// private 로 지정하는 이유는 해당클래스가아닌 다른 클래스에서 이 변수로 직접 접근을 하지 못하도록한다.

//	public Member(int id, String username, String password, String email) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.email = email;
//	}
//	
//	
//	public int getId() {
//		return id;
//	}
//
//
//	public String getUsername() {
//		return username;
//	}
//
//
//	public String getPassword() {
//		return password;
//	}
//
//
//	public String getEmail() {
//		return email;
//	}
//
//
//	public void setId(int id) {
//		this.id = id;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	
}
