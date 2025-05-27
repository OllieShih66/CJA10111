<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lutu.shoporder.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>
<%
ShopOrderService sos = new ShopOrderService();
List<ShopOrderVO> list = sos.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<title>所有商品訂單資料 - listAllEmp.jsp</title>

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
	width: 800px;
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
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有商品訂單資料 - listAllEmp.jsp</h3>
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
			<th>修改</th>
			
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="sovo" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

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

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shopOrder/shopOrder.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="shopOrderId" value="${sovo.shopOrderId}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/shopOrder/shopOrder.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="shopOrderId" value="${sovo.shopOrderId}"> <input --%>
<!-- 							type="hidden" name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->

			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>