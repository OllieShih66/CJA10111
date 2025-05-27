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
		dao.update(shopOrderVO);
		return shopOrderVO;
	}

	public ShopOrderVO getOneShopOrder(Integer shopOrderId) {
		return dao.findByPrimaryKey(shopOrderId);
	}

	public List<ShopOrderVO> getAll() {
		return dao.getAll();
	}

	
}
