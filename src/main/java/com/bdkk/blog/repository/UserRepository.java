package com.bdkk.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bdkk.blog.model.User;


// DAO
// 자동으로 bean등록이 된다.
// @Repository 생략 가능하다. //JpaRepository User테이블을 관리하는 놈, User테이블에 프라이머리 키는 Integer다
public interface UserRepository extends JpaRepository<User, Integer>{
//기본 CRUD만하려면 이곳에 아무것도 적지 않아도 된다.
	//SELECT * FROM user WHERE username =  1?;
	Optional<User> findByUsername(String username);
}
// JPA naming 쿼리 전략
// SELECT *FROM user WHERE username = ?1 AND password = ?2;
//	User findByUsernameAndPassword(String username, String password);

//	@Query(value ="SELECT *FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String name, String password);
