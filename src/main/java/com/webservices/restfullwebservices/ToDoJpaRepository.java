package com.webservices.restfullwebservices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoJpaRepository extends JpaRepository<Todo,Integer>{
	List<Todo> findByUsername(String username);
}
