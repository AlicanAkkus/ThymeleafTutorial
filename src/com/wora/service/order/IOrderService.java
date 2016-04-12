package com.wora.service.order;

import java.util.ArrayList;

import com.wora.bean.Order;

public interface IOrderService {

	public abstract void addOrder(Order order) throws Exception;

	public abstract void deleteOrder(Long orderId) throws Exception;

	public abstract void updateOrder(Order order) throws Exception;

	public abstract Order getOrder(Long id) throws Exception;

	public abstract ArrayList<Order> getOrders() throws Exception;

}