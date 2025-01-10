<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="add" accept-charset="utf-8" name ="new_todo" method="get">
	어떤 일인가요?<br>
	<input type="text" name= "title" required/><br>
	
	누가 할 일인가요?<br>
	<input type="text" name="name" required/><br>
	
	우선순위를 선택하세요<br>
	<input type="radio" name="sequence" value=1 required/>1순위
	<input type="radio" name="sequence" value=2/>2순위
	<input type="radio" name="sequence" value=3/>3순위<br>
	
	<input type = "submit" value="제출"/>
	<input type="reset" value="리셋"/>
</form>
<a href="main"><button>이전 화면</button></a>

</body>
</html>