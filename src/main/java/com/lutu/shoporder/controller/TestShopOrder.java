package com.lutu.shoporder.controller;

import java.util.ArrayList;
import java.util.List;

import com.lutu.shoporder.model.ShopOrderService;
import com.lutu.shoporder.model.ShopOrderVO;

public class TestShopOrder {

	public static void main(String[] args) {

		ShopOrderService sos = new ShopOrderService();

		// 新增資料
//		ShopOrderVO sovo1 = new ShopOrderVO();
//		sovo1.setShopOrderId(1999);
//		sovo1.setMemId(10000004);
//		sovo1.setShopOrderDate(LocalDateTime.now());	//由字串轉換localDateTime
//		sovo1.setShopOrderShipment((byte)1);
//		sovo1.setShopOrderShipFee(100);
//		sovo1.setBeforeDiscountAmount(9999);
//		sovo1.setDiscountCodeId(null);
//		sovo1.setDiscountAmount(null);
//		sovo1.setAfterDiscountAmount(10099);
//		sovo1.setShopOrderPayment((byte)1);
//		sovo1.setOrderName("林小強");
//		sovo1.setOrderEmail("user4@example.com");
//		sovo1.setOrderPhone("0955888777");
//		sovo1.setOrderShippingAddress("台中市西屯區台灣大道三段100號10樓");
//		sovo1.setShopOrderNote(null);
//		sovo1.setShopOrderShipDate(LocalDateTime.parse("2025-04-18T07:47:25"));
//		sovo1.setShopOrderStatus((byte)4);
//		sovo1.setShopReturnApply((byte)1);
//		
//		ShopOrderVO newsovo1 = sos.addShopOrder(sovo1);

		// 修改資料
//		ShopOrderVO sovo2 = new ShopOrderVO();
//
//		sovo2.setShopOrderDate(LocalDateTime.parse("2025-04-22T14:17:08"));
//		sovo2.setShopOrderShipment((byte) 2);
//		sovo2.setShopOrderShipFee(100);
//		sovo2.setBeforeDiscountAmount(2160);
//		sovo2.setDiscountCodeId(null);
//		sovo2.setDiscountAmount(null);
//		sovo2.setAfterDiscountAmount(1800);
//		sovo2.setShopOrderPayment((byte) 1); // 調整付款方式
//		sovo2.setOrderName("陳小雪");
//		sovo2.setOrderEmail("user5@example.com");
//		sovo2.setOrderPhone("0966123456");
//		sovo2.setOrderShippingAddress("超商取貨");
//		sovo2.setShopOrderNote(null);
//		sovo2.setShopOrderShipDate(LocalDateTime.parse("2025-04-24T14:17:08"));
//		sovo2.setShopOrderStatus((byte) 4);
//		sovo2.setShopReturnApply((byte) 0);
//		sovo2.setShopOrderId(1010);
//		sovo2.setMemId(10000005);
//
//		ShopOrderVO newsovo2 = sos.updateShopOrder(sovo2);

		// 單筆查詢
//		ShopOrderVO sovo3 = sos.getOneShopOrder(1002);
//		System.out.println(sovo3.getShopOrderId() + "\n" + sovo3.getMemId() + "\n" + sovo3.getShopOrderDate() + "\n"
//				+ sovo3.getShopOrderShipment() + "\n" + sovo3.getShopOrderShipFee() + "\n" + sovo3.getBeforeDiscountAmount()
//				+ "\n"  + sovo3.getDiscountCodeId() + "\n" + sovo3.getDiscountAmount() + "\n"
//				+ sovo3.getAfterDiscountAmount() + "\n" + sovo3.getShopOrderPayment() + "\n" + sovo3.getOrderName() + "\n" 
//				+ sovo3.getOrderEmail() + "\n"  + sovo3.getOrderPhone() + "\n"  + sovo3.getOrderShippingAddress() + "\n" 
//				+ sovo3.getShopOrderNote() + "\n"  + sovo3.getShopOrderShipDate() + "\n"  + sovo3.getShopOrderStatus() + "\n" 
//				+ sovo3.getShopReturnApply());
		

		// 多筆查詢
		List<ShopOrderVO> list = sos.getAll();
		
		for (ShopOrderVO sovo4 : list) {
			System.out.println(sovo4.getShopOrderId() + " " + sovo4.getMemId() + " " + sovo4.getShopOrderDate() + " "
					+ sovo4.getShopOrderShipment() + " " + sovo4.getShopOrderShipFee() + " " + sovo4.getBeforeDiscountAmount()
					+ " "  + sovo4.getDiscountCodeId() + " " + sovo4.getDiscountAmount() + " "
					+ sovo4.getAfterDiscountAmount() + " " + sovo4.getShopOrderPayment() + " " + sovo4.getOrderName() + " " 
					+ sovo4.getOrderEmail() + " "  + sovo4.getOrderPhone() + " "  + sovo4.getOrderShippingAddress() + " " 
					+ sovo4.getShopOrderNote() + " "  + sovo4.getShopOrderShipDate() + " "  + sovo4.getShopOrderStatus() + " " 
					+ sovo4.getShopReturnApply());
			
			System.out.println("===============================================================");
			
		}
		
		
		
		
		
		
		

	}

}
