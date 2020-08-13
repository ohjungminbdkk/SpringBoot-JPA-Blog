package com.bdkk.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//컨트롤이라는 어노테이션이 붙으면 파일을 리턴한다.

@Controller
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로: src/main/resource/static
		// 리턴명 : /home.html
		// 풀경로 : src/main/resource/static/home.html
		// 해당 경로 아래에 있는 html파일로 리턴을해준다
		// 스프링부트는 JSP를 지원을 해주지 않기떄문에 JSP 템플릿엔진을 pom.xml에 주입해야한다.
		return "/home.html";
	}
	

	//http://localhost:8000/blog/temp/img
	@GetMapping("/temp/img")
	public String tempImg() {
		// 브라우저가 인식할 수 있는 파일이 정적파일
		return "/a.png";
	}
	
	//http://localhost:8000/blog/temp/jsp
	@GetMapping("/temp/jsp")
	public String tempjsp() {
		// 브라우저가 인식할 수 있는 파일이 정적파일
		// jsp는 정적이 아닌 동적인 파일이다. 즉 java파일. 컴파일이 필요한 파일이기 때문에 불러들이지 못한다.
		return "/temp.jsp";
	}
	
	//http://localhost:8000/blog/temp1/jsp
	@GetMapping("/temp1/jsp")
	public String tempImg2() {
		// prefix : /WEB-INF/views
		// suffix : .jsp
		// 풀네임 : /WEB-INF/views/test.jsp
		// 이거 jsp파일이네 웹서버인 아파치야 네가 할수 있는 일은 아니니깐 톰캣 내가 처리할께 하면서 컴파일시켜 html로 보내줄께 그럼 웹브라우저가 지금처럼 이해할수있을거야.
		return "/test";
	}
}
