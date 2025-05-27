package com.lutu.shoporder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopOrderDAO implements ShopOrderDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/lutudb?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "j8nnieRu6yJane";

	private static final String INSERT_STMT = "INSERT INTO shop_order (shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,before_discount_amount,discount_code_id,discount_amount,after_discount_amount,shop_order_payment,order_name,order_email,order_phone,order_shipping_address,shop_order_note,shop_order_ship_date,shop_order_status,shop_return_apply) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE shop_order set shop_order_shipment = ?,shop_order_ship_fee = ?,before_discount_amount = ?,discount_code_id = ?,discount_amount = ?,after_discount_amount = ?,shop_order_payment = ?,order_name = ?,order_email = ?,order_phone = ?,order_shipping_address = ?,shop_order_note = ?,shop_order_ship_date = ?,shop_order_status = ?,shop_return_apply = ? where shop_order_id = ? AND mem_id = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM shop_order where shop_order_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM shop_order order by shop_order_id";
//	private static final String DELETE = "DELETE FROM shop_order where shop_order_id = ?";

	@Override
	public void insert(ShopOrderVO ShopOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ShopOrderVO.getShopOrderId());
			pstmt.setInt(2, ShopOrderVO.getMemId());
			pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(ShopOrderVO.getShopOrderDate()));
			pstmt.setByte(4, ShopOrderVO.getShopOrderShipment());
			pstmt.setInt(5, ShopOrderVO.getShopOrderShipFee());
			pstmt.setInt(6, ShopOrderVO.getBeforeDiscountAmount());
			pstmt.setString(7, ShopOrderVO.getDiscountCodeId());
			
			// 判斷是否為 null，然後用 setNull() 或 setInt()
			if(ShopOrderVO.getDiscountAmount() != null) {
				pstmt.setInt(8, ShopOrderVO.getDiscountAmount());
			} else {
				pstmt.setNull(8, java.sql.Types.INTEGER);
			}
			
			pstmt.setInt(9, ShopOrderVO.getAfterDiscountAmount());
			pstmt.setByte(10, ShopOrderVO.getShopOrderPayment());
			pstmt.setString(11, ShopOrderVO.getOrderName());
			pstmt.setString(12, ShopOrderVO.getOrderEmail());
			pstmt.setString(13, ShopOrderVO.getOrderPhone());
			pstmt.setString(14, ShopOrderVO.getOrderShippingAddress());
			pstmt.setString(15, ShopOrderVO.getShopOrderNote());
			pstmt.setTimestamp(16, java.sql.Timestamp.valueOf(ShopOrderVO.getShopOrderShipDate()));
			pstmt.setByte(17, ShopOrderVO.getShopOrderStatus());
			pstmt.setByte(18, ShopOrderVO.getShopReturnApply());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(ShopOrderVO ShopOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setByte(1, ShopOrderVO.getShopOrderShipment());
			pstmt.setInt(2, ShopOrderVO.getShopOrderShipFee());
			pstmt.setInt(3, ShopOrderVO.getBeforeDiscountAmount());
			pstmt.setString(4, ShopOrderVO.getDiscountCodeId());
			
			// 判斷是否為 null，然後用 setNull() 或 setInt()
			if(ShopOrderVO.getDiscountAmount() != null) {
			pstmt.setInt(5, ShopOrderVO.getDiscountAmount());
			} else {
				pstmt.setNull(5, java.sql.Types.INTEGER);
			}
			pstmt.setInt(6, ShopOrderVO.getAfterDiscountAmount());
			pstmt.setByte(7, ShopOrderVO.getShopOrderPayment());
			pstmt.setString(8, ShopOrderVO.getOrderName());
			pstmt.setString(9, ShopOrderVO.getOrderEmail());
			pstmt.setString(10, ShopOrderVO.getOrderPhone());
			pstmt.setString(11, ShopOrderVO.getOrderShippingAddress());
			pstmt.setString(12, ShopOrderVO.getShopOrderNote());
			pstmt.setTimestamp(13, java.sql.Timestamp.valueOf(ShopOrderVO.getShopOrderShipDate()));
			pstmt.setByte(14, ShopOrderVO.getShopOrderStatus());
			pstmt.setByte(15, ShopOrderVO.getShopReturnApply());
			pstmt.setInt(16, ShopOrderVO.getShopOrderId());
			pstmt.setInt(17, ShopOrderVO.getMemId());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public ShopOrderVO findByPrimaryKey(Integer shopOrderId) {
	
		ShopOrderVO so = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, shopOrderId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				so = new ShopOrderVO();
				so.setShopOrderId(rs.getInt("shop_order_id"));
				so.setMemId(rs.getInt("mem_id"));
				so.setShopOrderDate(rs.getTimestamp("shop_order_date").toLocalDateTime());	// 把 Timestamp 轉成 LocalDateTime
				so.setShopOrderShipment(rs.getByte("shop_order_shipment"));
				so.setShopOrderShipFee(rs.getInt("shop_order_ship_fee"));
				so.setBeforeDiscountAmount(rs.getInt("before_discount_amount"));
				so.setDiscountCodeId(rs.getString("discount_code_id"));
				so.setDiscountAmount(rs.getInt("discount_amount"));
				so.setAfterDiscountAmount(rs.getInt("after_discount_amount"));
				so.setShopOrderPayment(rs.getByte("shop_order_payment"));
				so.setOrderName(rs.getString("order_name"));
				so.setOrderEmail(rs.getString("order_email"));
				so.setOrderPhone(rs.getString("order_phone"));
				so.setOrderShippingAddress(rs.getString("order_shipping_address"));
				so.setShopOrderNote(rs.getString("shop_order_note"));
				so.setShopOrderShipDate(rs.getTimestamp("shop_order_ship_date").toLocalDateTime());
				so.setShopOrderStatus(rs.getByte("shop_order_status"));
				so.setShopReturnApply(rs.getByte("shop_return_apply"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return so;
	}

	@Override
	public List<ShopOrderVO> getAll() {
		List<ShopOrderVO> list = new ArrayList<ShopOrderVO>();
	
		ShopOrderVO so = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				so = new ShopOrderVO();
				so.setShopOrderId(rs.getInt("shop_order_id"));
				so.setMemId(rs.getInt("mem_id"));
				so.setShopOrderDate(rs.getTimestamp("shop_order_date").toLocalDateTime());	// 把 Timestamp 轉成 LocalDateTime
				so.setShopOrderShipment(rs.getByte("shop_order_shipment"));
				so.setShopOrderShipFee(rs.getInt("shop_order_ship_fee"));
				so.setBeforeDiscountAmount(rs.getInt("before_discount_amount"));
				so.setDiscountCodeId(rs.getString("discount_code_id"));
				so.setDiscountAmount(rs.getInt("discount_amount"));
				so.setAfterDiscountAmount(rs.getInt("after_discount_amount"));
				so.setShopOrderPayment(rs.getByte("shop_order_payment"));
				so.setOrderName(rs.getString("order_name"));
				so.setOrderEmail(rs.getString("order_email"));
				so.setOrderPhone(rs.getString("order_phone"));
				so.setOrderShippingAddress(rs.getString("order_shipping_address"));
				so.setShopOrderNote(rs.getString("shop_order_note"));
				so.setShopOrderShipDate(rs.getTimestamp("shop_order_ship_date").toLocalDateTime());
				so.setShopOrderStatus(rs.getByte("shop_order_status"));
				so.setShopReturnApply(rs.getByte("shop_return_apply"));
	
				list.add(so); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
