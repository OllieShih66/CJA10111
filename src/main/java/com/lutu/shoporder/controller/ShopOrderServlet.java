package com.lutu.shoporder.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import com.lutu.shoporder.model.ShopOrderService;
import com.lutu.shoporder.model.ShopOrderVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShopOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("shopOrderId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入商品訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/shopOrder/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer shopOrderId = null;
			try {
				shopOrderId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/shopOrder/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ShopOrderService sos = new ShopOrderService();
			ShopOrderVO sovo = sos.getOneShopOrder(shopOrderId);
			if (sovo == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/shopOrder/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("sovo", sovo); // 資料庫取出的ShopOrderVO物件,存入req
			String url = "/shopOrder/listOneShopOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllShopOrder.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer shopOrderId = Integer.valueOf(req.getParameter("shopOrderId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ShopOrderService sos = new ShopOrderService();
			ShopOrderVO sovo = sos.getOneShopOrder(shopOrderId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("sovo", sovo); // 資料庫取出的ShopOrderVO物件,存入req
			String url = "/shopOrder/update_shopOrder_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listOneShopOrder.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer shopOrderId = Integer.valueOf(req.getParameter("shopOrderId").trim());
			Integer memId = Integer.valueOf(req.getParameter("memId").trim());
			try {

				String shopOrderIdStr = req.getParameter("shopOrderId");
				if (shopOrderIdStr == null || shopOrderIdStr.trim().isEmpty()) {
					errorMsgs.add("訂單ID不可為空");
				} else {
					shopOrderId = Integer.valueOf(shopOrderIdStr.trim());
				}

				String memIdStr = req.getParameter("memId");
				if (memIdStr == null || memIdStr.trim().isEmpty()) {
					errorMsgs.add("會員ID不可為空");
				} else {
					memId = Integer.valueOf(memIdStr.trim());
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("訂單ID或會員ID格式錯誤，請填寫數字");
				req.setAttribute("sovo", new ShopOrderVO());
				RequestDispatcher failureView = req.getRequestDispatcher("/shopOrder/update_shopOrder_input.jsp");
				failureView.forward(req, res);
			}

			Byte shopOrderShipment = Byte.parseByte(req.getParameter("shopOrderShipment"));
			if (shopOrderShipment == null) {
				errorMsgs.add("出貨方式: 請勿空白");
			} else if (shopOrderShipment > 2 || shopOrderShipment == 0) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("出貨方式: 1: 賣家宅配 2: 超商取貨付款");
			}

			Integer shopOrderShipFee = null;
			try {
				shopOrderShipFee = Integer.valueOf(req.getParameter("shopOrderShipFee").trim());
				if (shopOrderShipFee == null || shopOrderShipFee < 0) {
					errorMsgs.add("運費請勿空白，並且不為負數");
				}
			} catch (NumberFormatException e) {
				shopOrderShipFee = 0;
				errorMsgs.add("運費請填數字.");
			}

			Integer beforeDiscountAmount = null;
			try {
				beforeDiscountAmount = Integer.valueOf(req.getParameter("beforeDiscountAmount").trim());
				if (beforeDiscountAmount == null || beforeDiscountAmount < 0) {
					errorMsgs.add("折價前總金額請勿空白，並且不為負數");
				}
			} catch (NumberFormatException e) {
				beforeDiscountAmount = 0;
				errorMsgs.add("折價前總金額請填數字.");
			}

			String discountCodeId = null;
			discountCodeId = req.getParameter("discountCodeId").trim();

			Integer discountAmount = null;
			try {
				discountAmount = Integer.valueOf(req.getParameter("discountAmount").trim());
				if (discountAmount < 0) {
					errorMsgs.add("折價金額不為負數");
				}
			} catch (NumberFormatException e) {
				discountAmount = 0;
				errorMsgs.add("折價金額請填數字.");
			}

			Integer afterDiscountAmount = null;
			try {
				afterDiscountAmount = Integer.valueOf(req.getParameter("afterDiscountAmount").trim());
				if (afterDiscountAmount == null || afterDiscountAmount < 0) {
					errorMsgs.add("實付金額請勿空白，並且不為負數");
				}
			} catch (NumberFormatException e) {
				afterDiscountAmount = 0;
				errorMsgs.add("實付金額請填數字.");
			}

			Byte shopOrderPayment = Byte.parseByte(req.getParameter("shopOrderPayment"));
			if (shopOrderPayment == null) {
				errorMsgs.add("付款方式: 請勿空白");
			} else if (shopOrderShipment > 4 || shopOrderShipment == 0) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("付款方式: 1: 信用卡 2: LINEPAY 3: 宅配取貨付款 4: 超商取貨付款");
			}

			String orderName = req.getParameter("orderName").trim();
			if (orderName == null || orderName.trim().length() == 0) {
				errorMsgs.add("訂購人姓名請勿空白");
			}
			String orderEmail = req.getParameter("orderEmail").trim();
			if (orderEmail == null || orderEmail.trim().length() == 0) {
				errorMsgs.add("訂購人郵件請勿空白");
			}
			String orderPhone = req.getParameter("orderPhone").trim();
			if (orderPhone == null || orderPhone.trim().length() == 0) {
				errorMsgs.add("訂購人手機請勿空白");
			}
			String orderShippingAddress = req.getParameter("orderShippingAddress").trim();
			if (orderShippingAddress == null || orderShippingAddress.trim().length() == 0) {
				errorMsgs.add("訂購人地址請勿空白");
			}

			String shopOrderNote = null;
			shopOrderNote = req.getParameter("shopOrderNote").trim();

			LocalDateTime shopOrderShipDate = null;
			try {
				String dateStr = req.getParameter("date").trim();
				String timeStr = req.getParameter("time").trim();
				String dateTimeStr = dateStr + "T" + (timeStr.contains(":") ? timeStr : timeStr + ":00");
				shopOrderShipDate = LocalDateTime.parse(dateTimeStr);

			} catch (DateTimeParseException e) {
				errorMsgs.add("出貨日期格式錯誤，應為 YYYY-MM-DD HH:MM:SS");
			}

			Byte shopOrderStatus = Byte.parseByte(req.getParameter("shopOrderStatus"));
			if (shopOrderStatus == null) {
				errorMsgs.add("訂單狀態: 請勿空白");
			} else if (shopOrderShipment > 5 || shopOrderShipment == null) {
				errorMsgs.add("訂單狀態: 0: 等待付款中 1: 已取消 2: 等待賣家確認中 3: 準備出貨中 4: 已出貨 5: 未取貨，退回賣家 ");
			}

			Byte shopReturnApply = Byte.parseByte(req.getParameter("shopReturnApply"));
			if (shopReturnApply == null) {
				errorMsgs.add("付款方式: 請勿空白");
			} else if (shopReturnApply > 3 || shopReturnApply == null) {
				errorMsgs.add("付款方式: 0: 未申請退貨 1: 申請退貨 2: 退貨成功 3: 退貨失敗 ");
			}

			ShopOrderVO sovo = new ShopOrderVO();
			sovo.setShopOrderShipment(shopOrderShipment);
			sovo.setShopOrderShipFee(shopOrderShipFee);
			sovo.setBeforeDiscountAmount(beforeDiscountAmount);
			sovo.setDiscountCodeId(discountCodeId);
			sovo.setDiscountAmount(discountAmount);
			sovo.setAfterDiscountAmount(afterDiscountAmount);
			sovo.setShopOrderPayment(shopOrderPayment);
			sovo.setOrderName(orderName);
			sovo.setOrderEmail(orderEmail);
			sovo.setOrderPhone(orderPhone);
			sovo.setOrderShippingAddress(orderShippingAddress);
			sovo.setShopOrderNote(shopOrderNote);
			sovo.setShopOrderShipDate(shopOrderShipDate);
			sovo.setShopOrderStatus(shopOrderStatus);
			sovo.setShopReturnApply(shopReturnApply);
			sovo.setShopOrderId(shopOrderId);
			sovo.setMemId(memId);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("sovo", sovo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/shopOrder/update_shopOrder_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ShopOrderService sos = new ShopOrderService();
			sovo = sos.updateShopOrder(sovo); // 使用 ShopOrderVO 方法

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("sovo", sovo); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/shopOrder/listOneShopOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

//		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			String ename = req.getParameter("ename");
//			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			if (ename == null || ename.trim().length() == 0) {
//				errorMsgs.add("員工姓名: 請勿空白");
//			} else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}
//
//			String job = req.getParameter("job").trim();
//			if (job == null || job.trim().length() == 0) {
//				errorMsgs.add("職位請勿空白");
//			}
//
//			java.sql.Date hiredate = null;
//			try {
//				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//			} catch (IllegalArgumentException e) {
//				hiredate = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
//
//			Double sal = null;
//			try {
//				sal = Double.valueOf(req.getParameter("sal").trim());
//			} catch (NumberFormatException e) {
//				sal = 0.0;
//				errorMsgs.add("薪水請填數字.");
//			}
//
//			Double comm = null;
//			try {
//				comm = Double.valueOf(req.getParameter("comm").trim());
//			} catch (NumberFormatException e) {
//				comm = 0.0;
//				errorMsgs.add("獎金請填數字.");
//			}
//
//			Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());
//
//			EmpVO empVO = new EmpVO();
//			empVO.setEname(ename);
//			empVO.setJob(job);
//			empVO.setHiredate(hiredate);
//			empVO.setSal(sal);
//			empVO.setComm(comm);
//			empVO.setDeptno(deptno);
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 2.開始新增資料 ***************************************/
//			EmpService empSvc = new EmpService();
//			empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
//
//			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/emp/listAllEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//		}

//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//				/***************************1.接收請求參數***************************************/
//				Integer empno = Integer.valueOf(req.getParameter("empno"));
//				
//				/***************************2.開始刪除資料***************************************/
//				EmpService empSvc = new EmpService();
//				empSvc.deleteEmp(empno);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//		}
	}

}
