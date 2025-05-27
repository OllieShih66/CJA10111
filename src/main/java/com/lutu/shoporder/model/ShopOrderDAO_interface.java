package com.lutu.shoporder.model;

import java.util.List;

public interface ShopOrderDAO_interface {

	public void insert(ShopOrderVO ShopOrderVO);

	public void update(ShopOrderVO ShopOrderVO);

//	public void delete(int shopOrderId);

	public ShopOrderVO findByPrimaryKey(Integer shopOrderId);

	public List<ShopOrderVO> getAll();
}
