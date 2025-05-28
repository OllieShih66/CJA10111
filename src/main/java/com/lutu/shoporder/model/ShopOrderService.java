package com.lutu.shoporder.model;

import java.time.LocalDateTime;
import java.util.List;

public class ShopOrderService {
	private ShopOrderDAO_interface dao;

	public ShopOrderService() {
		dao = new ShopOrderDAO();
	}

	public ShopOrderVO addShopOrder(ShopOrderVO shopOrderVO) {
		dao.insert(shopOrderVO);
		return shopOrderVO;
	}


	public ShopOrderVO updateShopOrder(ShopOrderVO shopOrderVO) {
	    if (shopOrderVO == null) {
	        throw new IllegalArgumentException("ShopOrderVO 不可為空");
	    }
	    if (shopOrderVO.getShopOrderId() == null || shopOrderVO.getMemId() == null) {
	        throw new IllegalArgumentException("訂單ID或會員ID不可為空");
	    }
	    
	    try {
	        dao.update(shopOrderVO);
	        return shopOrderVO;
	    } catch (RuntimeException e) {
	        throw new RuntimeException("更新訂單失敗: " + e.getMessage());
	    }
	}

	public ShopOrderVO getOneShopOrder(Integer shopOrderId) {
		return dao.findByPrimaryKey(shopOrderId);
	}

	public List<ShopOrderVO> getAll() {
		return dao.getAll();
	}

//	public ShopOrderVO updateShopOrder(Byte shopOrderShipment, Integer shopOrderShipFee, Integer beforeDiscountAmount,
//			String discountCodeId, Integer discountAmount, Integer afterDiscountAmount, Byte shopOrderPayment,
//			String orderName, String orderEmail, String orderPhone, String orderShippingAddress, String shopOrderNote,
//			LocalDateTime shopOrderShipDate, Byte shopOrderStatus, Byte shopReturnApply, Integer shopOrderId,
//			Integer memId) {
//		ShopOrderVO sovo = new ShopOrderVO();
//		return sovo;
//	}

}
