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

	<table id="table-1">
		<tr>
			<td>
				<h3>營地資料修改 - update_shopOrder_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="image/logo.svg" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="shopOrder.do" name="form1">
		<table>
			<tr>
				<%-- <td>營地主編號:<font color=red><b>*</b></font></td>
		<td><%=campVO.getCampId()%></td> --%>

				<td>商品訂單編號: <span style="color: red;"><b>*</b></span></td>
				<td><%=sovo.getShopOrderId()%></td>
			</tr>
			<tr>
				<%-- <td>營地主編號:<font color=red><b>*</b></font></td>
		<td><%=campVO.getCampId()%></td> --%>

				<td>露營者編號: <span style="color: red;"><b>*</b></span></td>
				<td><%=sovo.getMemId()%></td>
			</tr>
			<tr>
				<td>訂單日期:</td>
				<td><input type="TEXT" name="campName"
					value="<%=sovo.getShopOrderDate()%>" size="45" /></td>
			</tr>
			<tr>
				<td>出貨方式:</td>
				<td><select size="1" name="shopOrderShipment">
						<option value=1>賣家宅配
						<option value=2>超商取貨付款
				</select></td>
			</tr>
			<tr>
				<td>運費:</td>
				<td><input name="shopOrderShipFee" id="ship_fee" type="text"
					value="<%=sovo.getShopOrderShipFee()%>" size="45" /></td>
			</tr>
			<tr>
				<td>折價前總金額:</td>
				<td><input name="beforeDiscountAmount" id="before_discount"
					type="text" value="<%=sovo.getBeforeDiscountAmount()%>" size="45" /></td>
			</tr>
			<tr>
				<td>折價券編號:</td>
				<td><input name="discountCodeId" id="discount_code" type="text"
					value="<%=sovo.getDiscountCodeId()%>" size="45" /></td>
			</tr>
			<tr>
				<td>折價金額:</td>
				<td><input name="discountAmount" id="discount_amount"
					type="text" value="<%=sovo.getDiscountAmount()%>" size="45" /></td>
			</tr>
			<tr>
				<td>實付金額:</td>
				<td><input name="afterDiscountAmount" id="after_discount"
					type="text" value="<%=sovo.getAfterDiscountAmount()%>" size="45" /></td>
			</tr>
			<tr>
				<td>付款方式:</td>
				<td><select size="1" name="shopOrderPayment">
						<option value=1>信用卡
						<option value=2>LINEPAY
						<option value=3>宅配取貨付款
						<option value=4>超商取貨付款
				</select></td>
			</tr>
			<tr>
				<td>訂購人姓名:</td>
				<td><input name="orderName" id="order_name" type="text"
					value="<%=sovo.getOrderName()%>" size="45" /></td>
			</tr>
			<tr>
				<td>訂購人郵件:</td>
				<td><input name="orderEmail" id="order_email" type="text"
					value="<%=sovo.getOrderEmail()%>" size="45" /></td>
			</tr>
			<tr>
				<td>訂購人手機:</td>
				<td><input name="orderPhone" id="order_phone" type="text"
					value="<%=sovo.getOrderPhone()%>" size="45" /></td>
			</tr>
			<tr>
				<td>訂購人地址:</td>
				<td><input name="orderShippingAddress" id="order_address"
					type="text" value="<%=sovo.getOrderShippingAddress()%>" size="45" /></td>
			</tr>
			<tr>
				<td>訂購備註:</td>
				<td><input name="shopOrderNote" id="order_note" type="text"
					value="<%=sovo.getShopOrderNote()%>" size="45" /></td>
			</tr>
			<tr>
				<td>出貨日期:</td>
				<td><label for="date">請選擇日期：</label> <input type="date"
					id="date" name="date" required> <br> <label for="time">請選擇時間：</label>
					<input type="time" id="time" name="time" required> <br>
			</tr>
			<tr>
				<td>訂單狀態:<font color=red><b>*</b></font></td>
				<td><select size="1" name="shopOrderStatus">
						<option value=0>等待付款中
						<option value=1>已取消
						<option value=2>等待賣家確認中
						<option value=3>準備出貨中
						<option value=4>已出貨
						<option value=5>未取貨，退回賣家
				</select></td>
			</tr>
			<tr>
				<td>退貨申請:<font color=red><b>*</b></font></td>
				<td><select size="1" name="shopReturnApply">
						<option value=0>未申請退貨
						<option value=1>申請退貨
						<option value=1>退貨成功
						<option value=1>退貨失敗
				</select></td>
			</tr>
			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
	</tr> --%>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="shopOrderId" value="<%=sovo.getShopOrderId()%>">
		<input type="hidden" name="memId" value="<%=sovo.getMemId()%>">
		<input type="submit" value="送出修改">
	</FORM>
</body>





</html>