package com.water_order.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.validation.annotation.Validated;

import com.water_order.rest.dao.OrderDAO;
import com.water_order.rest.model.Order;
import com.water_order.rest.model.Orders;

@RestController
@Validated
@RequestMapping(path = "/orders")
public class OrderController 
{
    @Autowired
    private OrderDAO orderDao;
    
    @GetMapping(path="/", produces = "application/json")
    public Orders getOrders() 
    {
        return orderDao.getAllOrders();
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addOrder(@Valid
                        @RequestBody Order order) 
                 throws Exception 
    {       
        //Generate resource id
        Integer id = orderDao.getAllOrders().getOrderList().size() + 1;
        order.setId(id);
        
        //add resource
        orderDao.addOrder(order);
        
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(order.getId())
                                    .toUri();
        
        //Send location in response
        return ResponseEntity.created(location).build();
    }
}
