package com.water_order.rest.dao;

import org.springframework.stereotype.Repository;

import com.water_order.rest.model.Order;
import com.water_order.rest.model.Orders;

@Repository
public class OrderDAO 
{
    private static Orders list = new Orders();
       
    public Orders getAllOrders() 
    {
        return list;
    }
    
    public void addOrder(Order order) {
        list.getOrderList().add(order);
    }
}
