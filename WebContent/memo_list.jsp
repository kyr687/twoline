<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<%@ page import="java.util.ArrayList" %>
<%@ page import="twoline.OnelineDAO" %>
<%@ page import="twoline.OnelineDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
	background: #D5D5D5;
	padding: 20px;	
	}
	h2{
		text-align: center;
		color: #F15F5F;
	}
	#signup {
	background:#fff;
	border:1px solid #222;
	border-radius: 5px;
	padding: 20px;
	width: 400px;	
	margin:30px auto;
	}
		
	#signup fieldset {
	border: 1px solid #ccc;
	margin-bottom: 30px;
	}
		
	#signup legend {
	font-size: 16px;
	font-weight: bold;
	padding-left:5px;
	padding-bottom: 10px;	
	}
</style>
</head>
<%
	OnelineDAO dao = new OnelineDAO();
	ArrayList<OnelineDTO> dtos = dao.getList();
%>
<body>
<h2><b>메모장</b></h2><hr>

<form id="signup" method="post" action="memo_write.jsp">
	<fieldset>
	<legend>오늘의 일기</legend>
	<input type="text" name="memo"> </input>
	<input type="submit" value="등록"></input>
	<p>(4/7 15:13)오늘은 금요일이다. 우하하^^<input type="button" value="x"></input></p>
	<p>(4/7 11:03)수업 언제 끝나냐..<input type="button" value="x"></input></p>
	<img src="picnic.jfif" height=300px;>
	</fieldset>
</form>	
<hr>
	<div>
<%
	for(int i = 0; i < dtos.size() ; i++) {
		OnelineDTO dto = dtos.get(i);
		out.println("<p>(" + dto.getWdate() + ") " + dto.getMemo() + 
				"<input type=\"button\" value=\"X\" onClick=\"location.href='memo_delete.jsp?no=" + dto.getNo() + "'\"><br>");
		out.println("</input></p>");
	}
%>		
	</div>	
</body>
</html>