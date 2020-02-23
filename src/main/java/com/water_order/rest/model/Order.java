package com.water_order.rest.model;

public class Order {

    public Order() {

    }

    public Order(Integer id, String farmId, String startTime, String duration, String status) {
        super();
        this.id = id;
        this.farmId = farmId;
        this.startTime = startTime;
        this.duration = duration;
        this.status = status;
    }
 
    private Integer id;
    private String farmId;   
    private String startTime;
    private String duration;
    private String status;

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

    public String getduration() {
        return duration;
    }

    public void setduration(String duration) {
        this.duration = duration;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", farmId=" + farmId + ", startTime=" + startTime + ", duration=" + duration + ", status=" + status+ ""]";
    }
}
