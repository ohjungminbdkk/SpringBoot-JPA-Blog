package com.bdkk.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.print.attribute.standard.RequestingUserName;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bdkk.blog.model.RoleType;
import com.bdkk.blog.model.User;
import com.bdkk.blog.repository.UserRepository;

// html파일이 아니라 data를 리턴해주는 Controller = RestController
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입 DI
	private UserRepository userRepository;

	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
		userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 " +id+"는 DB에 없습니다.";
		}
		return "삭제되었습니다. " + id;
	}

	// save함수는 id를 전달하지 않으면 insert를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
	// email, password 받아야한다.
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 => Java
																					// Obeject(MessageConverter의
																					// Jackson라이브러리가 받아준다.)
		System.out.println("id :" + id);
		System.out.println("password :" + requestUser.getPassword());
		System.out.println("email :" + requestUser.getEmail());

		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패 하였습니다.");

		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

//		userRepository.save(user);

		// 더티 체킹
		return user;
	}

	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	// http://localhost:8000/blog/dummy/user/page
	@GetMapping("/dummy/user")
	public List<User> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);

		List<User> users = pagingUser.getContent();
		return users;
	}

	// {id} 주소로 파라메타를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐
		// 그럼 return null 이 리턴이 되자나.. 그럼 프로그램에 문제가 있지 않겠니?
		// Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해!!
		// 즉 User 빈객체를 보낸다는 것이다. 이것은 적어도 null이 아니기때문에
		// User user = userRepository.findById(id).get(); //절대 실수가 없어? 이런 안일한 일로 이건 좀 위험
//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				// TODO Auto-generated method stub
//				
//				return new User();
//			}
//		});

		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessgeConverter가 Jackson이라는 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에 던져지게 됩니다.
		return user;
	}

	// http://localhost://8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	// insert하기위해서 post
	@PostMapping("/dummy/join")
//	public String joint(@RequestParam("username") String username, String password, String email) {
	public String join(User user) {

		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("role : " + user.getRole());
		System.out.println("createDate : " + user.getCreateDate());

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";

	}

}
