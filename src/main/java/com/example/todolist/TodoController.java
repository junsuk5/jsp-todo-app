package com.example.todolist;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TodoController", urlPatterns = {"*.do"})
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = req.getRequestURI().substring(req.getContextPath().length());

		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		
		
		TodoRepository repository = TodoRepository.getInstance();
		
		if (command.equals("/addTodo.do")) {
			String text = req.getParameter("text");
			Todo todo = new Todo(TodoRepository.id++, text);

			repository.add(todo);
			
//			req.setAttribute("todos", repository.getAll());
//			
//			RequestDispatcher rd = req.getRequestDispatcher("todolist.jsp");
//			rd.forward(req, resp);
			
		} else if (command.equals("/toggleTodo.do")) {
			String id = req.getParameter("id");
			repository.toggle(Long.parseLong(id));
			
//			req.setAttribute("todos", repository.getAll());
			
//			RequestDispatcher rd = req.getRequestDispatcher("todolist.jsp");
//			rd.forward(req, resp);
		} else if (command.equals("/removeTodo.do")) {
			String id = req.getParameter("id");
			repository.remove(Long.parseLong(id));
		}
		
	}

}
