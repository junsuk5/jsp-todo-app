package com.example.todolist;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
	public static long id = 0;
	
	private static TodoRepository instance = new TodoRepository();
	
	private List<Todo> todos = new ArrayList<>();
	
	public static TodoRepository getInstance() {
		return instance;
	}
	
	public List<Todo> getAll() {
		return todos;
	}
	
	public void add(Todo todo) {
		todos.add(todo);
	}
	
	public void remove(Todo todo) {
		todos.remove(todo);
	}
	
	public void remove(long id) {
		Todo removeTodo = null;
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				removeTodo = todo;
				break;
			}
		}
		
		todos.remove(removeTodo);
	}
	
	public void update(Todo todo) {
		int index = todos.indexOf(todo);
		todos.set(index, todo);
	}
	
	public void toggle(long id) {
		for (Todo todo : todos) {
			if (todo.getId() == id) {
				todo.setChecked(!todo.isChecked());
				break;
			}
		}
	}
}
