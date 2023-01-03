<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>include 지시어</h1>
	<!-- 매 페이지마다 보여져야 하는 것들 따로 jsp로 만들어두기 -->
	<div style="border:1px solid black">
		<%@ include file="01_scriptingElement.jsp" %>
	</div>
	
	포함하고 있는 jsp안에 선언되어있는 변수를 현재 이 페이지에서도(include한 이후부터) 사용 가능 <br>
	1에서부터 100까지 총 합계 : <%= sum %>
</body>
</html>