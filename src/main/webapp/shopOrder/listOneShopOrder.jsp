<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lutu.shoporder.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ShopOrderVO sovo = (ShopOrderVO) request.getAttribute("ShopOrderVO"); //ShopOrderServlet.java(Concroller), 存入req的ShopOrderVO物件
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品訂單 - listOneCamp.jsp</title>
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
</head>

<body bgcolor="white">
	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>商品訂單資料 - listOneCamp.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="image/logo.svg" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>商品訂單編號</th>
			<th>露營者編號</th>
			<th>訂單日期</th>
			<th>出貨方式</th>
			<th>運費</th>
			<th>折價前總金額</th>
			<th>折價券編號</th>
			<th>折價金額</th>
			<th>實付金額</th>
			<th>實付金額</th>
			<th>訂購人姓名</th>
			<th>訂購人郵件</th>
			<th>訂購人手機</th>
			<th>訂購人地址</th>
			<th>訂單備註</th>
			<th>出貨日期</th>
			<th>訂單狀態</th>
			<th>訂單狀態</th>
			
			
		</tr>
		<tr>
			<td>${sovo.shopOrderId}</td>
			<td>${sovo.memId}</td>
			<td>${sovo.shopOrderDate}</td>
			<td>${sovo.shopOrderShipment}</td>
			<td>${sovo.shopOrderShipFee}</td>
			<td>${sovo.beforeDiscountAmount}</td>
			<td>${sovo.discountCodeId}</td>
			<td>${sovo.discountAmount}</td>
			<td>${sovo.afterDiscountAmount}</td>
			<td>${sovo.shopOrderPayment}</td>
			<td>${sovo.orderName}</td>
			<td>${sovo.orderEmail}</td>
			<td>${sovo.orderPhone}</td>
			<td>${sovo.orderShippingAddress}</td>
			<td>${sovo.shopOrderNote}</td>
			<td>${sovo.shopOrderShipDate}</td>
			<td>${sovo.shopOrderStatus}</td>
			<td>${sovo.shopOrderStatus}</td>
		</tr>
	</table>

</body>
</html>