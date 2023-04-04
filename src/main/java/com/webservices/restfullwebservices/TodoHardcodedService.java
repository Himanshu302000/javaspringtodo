package com.webservices.restfullwebservices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {
	private static List<Todo>todos = new ArrayList<Todo>();
	private static Integer idCounter = 0;
	static {
		todos.add(new Todo(++idCounter,"Himanshu","Learn Angular",new Date(),false));
		todos.add(new Todo(++idCounter,"abc","Spring Booy",new Date(),false));
		todos.add(new Todo(++idCounter,"Himanshu","Learn DSA",new Date(),false));
		todos.add(new Todo(++idCounter,"Himanshu","Anything",new Date(),false));}
	
	public List<Todo> findAll()
	{
		return todos;
	}
	
	public Todo deleteById(int id)
	{
		Todo todo=findById(id);
		if(todo==null) return null;
		if(todos.remove(todo)) return todo;
		return null;
		
	}

	public Todo findById(int id) {
		for(Todo todo:todos)
		{
			if(todo.getId()==id)
			{
				return todo;
			}
		}
		return null;
	}
	
	public Todo save(Todo todo)
	{
		
		if(todo.getId()==0 || todo.getId()==-1)
		{
			todo.setId(++idCounter);
			todos.add(todo);
		}
		else
		{
			deleteById(todo.getId());
			todos.add(todo);
		}
		return todo;
	}
}
