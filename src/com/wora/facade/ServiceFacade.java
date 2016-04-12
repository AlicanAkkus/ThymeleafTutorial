package com.wora.facade;

import java.util.ArrayList;

import com.wora.bean.Order;
import com.wora.service.order.IOrderService;
import com.wora.service.order.OrderService;

public class ServiceFacade {
	private static ServiceFacade facade;
	private IOrderService orderService;
	
	private ServiceFacade(){
		
	}
	
	public static ServiceFacade getInstance(){
		if(facade == null){
			facade = new ServiceFacade();
		}
		
		return facade;
	}
	
	public void startService(){
		
		orderService = new OrderService();
		
	}
	
	public void stopService(){
		orderService = null;
	}
	
	public void addOrder(Order order) throws Exception{
		orderService.addOrder(order);
	}

	public void deleteOrder(Order order) throws Exception{
		orderService.deleteOrder(order);
	}

	public void updateOrder(Order order) throws Exception{
		orderService.updateOrder(order);
	}

	public Order getOrder(Long id) throws Exception{
		return orderService.getOrder(id);
	}

	public ArrayList<Order> getOrders() throws Exception{
		return orderService.getOrders();
	}
}
