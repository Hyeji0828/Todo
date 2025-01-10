<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

Hello This is your Todo List<br>

<%@ page import="dto.Todo" %>
<%@ page import="java.util.List" %>

<%
    List<Todo> todoList = (List<Todo>)request.getAttribute("todoList");
	List<Todo> doingList = (List<Todo>)request.getAttribute("doingList");
	List<Todo> doneList = (List<Todo>)request.getAttribute("doneList");
%>

<div>
<a href="/Todo/form">새로운 할 일 등록</a>
</div>

<div>
<h1>TODO</h1>
<c:forEach items="${todoList }" var="item" varStatus="i">
	<div>
		<h3><c:out value="${item.title}"></c:out></h3>
		<div><c:out value="등록날짜:${item.regDate}, ${item.name}, 우선순위 ${item.sequence}"></c:out></div>
		
		<form action="type" method="get">
			<input type="submit" value="→"/>
			<input type="hidden" name="id" value="${item.id }"/>
		</form>
		
		
		
	</div>
</c:forEach>
</div>

<div>
<h1>DOING</h1>
<c:forEach items="${doingList }" var="item" varStatus="i">
	<div>
		<h3><c:out value="${item.title}"></c:out></h3>
		<div><c:out value="등록날짜:${item.regDate}, ${item.name}, 우선순위 ${item.sequence}"></c:out></div>
		
		<form action="type" method="get">
			<input type="submit" value="→"/>
			<input type="hidden" name="id" value="${item.id }"/>
		</form>
		
	</div>
</c:forEach>
</div>

<div>
<h1>DONE</h1>
<c:forEach items="${doneList }" var="item" varStatus="i">
	<div>
		<h3><c:out value="${item.title}"></c:out></h3>
		<div><c:out value="등록날짜:${item.regDate}, ${item.name}, 우선순위 ${item.sequence}"></c:out></div>
	</div>
</c:forEach>
</div>

</body>
</html>