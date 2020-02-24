package com.water_order.rest.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Digits;

public class Order {

    public Order() {

    }

    public Order(Integer id, String farmId, String startTime, Integer duration, OrderStatus status) {
        super();
        this.id = id;
        this.farmId = farmId;
        this.startTime = startTime;
        this.duration = duration;
        this.status = status;
    }
 
    private Integer id;

    @NotEmpty(message = "farmId must not be empty")
    private String farmId;   

    @NotEmpty(message = "startTime must not be empty")
    private String startTime;

    @Integer(message = "Duration in hours needs to be an integer")
    private Integer duration;

    private OrderStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getfarmId() {
        return farmId;
    }

    public void setfarmId(String farmId) {
        this.farmId = farmId;
    }

    public String getstartTime() {
        return startTime;
    }

    public void setstartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getduration() {
        return duration;
    }

    public void setduration(Integer duration) {
        this.duration = duration;
    }

    public OrderStatus getstatus() {
        return status;
    }

    public void setstatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", farmId=" + farmId + ", startTime=" + startTime + ", duration=" + duration + ", status=" + status + "]";
    }
}
