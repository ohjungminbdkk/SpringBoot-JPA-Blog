package com.bdkk.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdkk.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
