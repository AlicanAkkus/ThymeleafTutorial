package com.wora.service.order;

import java.util.ArrayList;
import java.util.HashMap;
import com.wora.bean.Order;

public class OrderService implements IOrderService {

	private HashMap<Long, Order> orderMap = new HashMap<>();

	/* (non-Javadoc)
	 * @see com.wora.service.order.IOrderService#addOrder(com.wora.bean.Order)
	 */
	@Override
	public void addOrder(Order order) throws Exception {

		if (orderMap.containsKey(order.getId())) {
			throw new RuntimeException(order.getId() + " has already added!");
		}

		orderMap.put(order.getId(), order);
	}

	/* (non-Javadoc)
	 * @see com.wora.service.order.IOrderService#deleteOrder(com.wora.bean.Order)
	 */
	@Override
	public void deleteOrder(Long orderId) throws Exception {

		if (!orderMap.containsKey(orderId)) {
			throw new RuntimeException(orderId + " not found!");
		}

		orderMap.remove(orderId);
	}

	/* (non-Javadoc)
	 * @see com.wora.service.order.IOrderService#updateOrder(com.wora.bean.Order)
	 */
	@Override
	public void updateOrder(Order order) throws Exception {

		if (!orderMap.containsKey(order.getId())) {
			throw new RuntimeException(order.getId() + " not found!");
		}

		orderMap.remove(order.getId());
		orderMap.put(order.getId(), order);
		
	}

	/* (non-Javadoc)
	 * @see com.wora.service.order.IOrderService#getOrder(java.lang.Long)
	 */
	@Override
	public Order getOrder(Long id) throws Exception {

		if (!orderMap.containsKey(id)) {
			throw new RuntimeException(id + " not found!");
		}

		return orderMap.get(id);
	}
	
	/* (non-Javadoc)
	 * @see com.wora.service.order.IOrderService#getOrders()
	 */
	@Override
	public ArrayList<Order> getOrders() throws Exception {
		return new ArrayList<Order>(orderMap.values());
	}

}
