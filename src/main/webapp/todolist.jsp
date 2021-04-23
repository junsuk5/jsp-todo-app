<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.example.todolist.Todo"%>
<%@ page import="com.example.todolist.TodoRepository"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="todolist.css" />
<link rel="stylesheet" type="text/css" href="form.css" />
<link rel="stylesheet" type="text/css" href="todoitem.css" />
<meta charset="UTF-8">
<title>Todo List</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function setDone(id) {
	  $.ajax({
		 url: "toggleTodo.do",
		 type: "post",
		 data: {"id": id},
		 success: function(data) {
			 window.location.reload();	 
		 }
	  });
}

function addTodo() {
	var text = $("input#text").val();
	  $.ajax({
		 url: "addTodo.do",
		 type: "post",
		 data: {"text": text},
		 success: function(data) {
			 window.location.reload();	 
		 }
	  });
}

function remove(id) {
	$.ajax({
		 url: "removeTodo.do",
		 type: "post",
		 data: {"id": id},
		 success: function(data) {
			 window.location.reload();	 
		 }
	  });
}

</script>
</head>
<body>

	<%
	TodoRepository repository = TodoRepository.getInstance();
	session.setAttribute("todos", repository.getAll());
	%>
	<div class="todo-list-template">
		<div class="title">오늘 할 일</div>

		<section class="form-wrapper">
			<div class="form">
				<input name="text" id="text" />
				<div class="create-button" onclick="addTodo();">추가</div>
			</div>
		</section>

		<section class="todos-wrapper">
			<c:forEach items="${todos}" var="todo">
				<div class="todo-item" onclick="setDone(${todo.getId()});">
					<div class="remove"
						onclick="event.stopPropagation(); remove(${todo.getId()});">&times;</div>
					<div class="todo-text ${todo.isChecked() ? 'checked' : ''}">${todo.getText()}</div>
					<c:if test="${todo.isChecked() == true}">
						<div class="check-mark">&#x2713;</div>
					</c:if>
				</div>
			</c:forEach>
		</section>
	</div>
</body>
</html>