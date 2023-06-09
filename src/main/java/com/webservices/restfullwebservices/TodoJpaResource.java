package com.webservices.restfullwebservices;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin("http://localhost:4200/")
@RestController
public class TodoJpaResource {
		
		@Autowired
		private TodoHardcodedService service;
		
		@Autowired
		private ToDoJpaRepository todoJpaRepository;
		@GetMapping("/jpa/users/{username}/todos")
		public List<Todo> getAllTodos(@PathVariable String username)
		{
			//return todoJpaRepository.findByUsername("Himanshu");
			return todoJpaRepository.findAll();
		} 
		
		@GetMapping("/jpa/users/{username}/todos/{id}")
		public Todo getTodo(@PathVariable String username,@PathVariable int id)
		{
			return todoJpaRepository.findById(id).get();
			//return service.findById(id);
		} 
		
		@DeleteMapping("/jpa/users/{username}/todos/{id}")
		public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable int id)
		{		
			 todoJpaRepository.deleteById(id);
			 return ResponseEntity.noContent().build();
		}
		
		@PutMapping("/jpa/users/{username}/todos/{id}")
		public ResponseEntity<Todo> updateTodo(@PathVariable String username,@PathVariable int id,@RequestBody Todo todo)
		{
			todoJpaRepository.save(todo);
			
			return new ResponseEntity<Todo>(todo,HttpStatus.OK);
		}
		
		@PostMapping("/jpa/users/{username}/todos")
		public ResponseEntity<Void>updateTodo(@PathVariable String username,@RequestBody Todo todo)
		{
			Todo createdTodo = todoJpaRepository.save(todo);
			URI uri=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		
		
		
}
