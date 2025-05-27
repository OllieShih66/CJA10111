<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="com.lutu.shoporder.model.*"%>

<%
//見com.lutu.shoporder.controller.ShopOrderServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
ShopOrderVO sovo = (ShopOrderVO) request.getAttribute("sovo");
%>



<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>營地資料修改 - update_camp_input.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	
</body>





</html>