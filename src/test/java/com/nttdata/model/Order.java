package com.nttdata.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.sql.Date;

@JsonPropertyOrder({"id", "petId", "quantity", "shipDate","status", "complete" })
public class Order    {
    private int id;
    private  int pedId;
    private int quantity;
    private Date shipDate;
    private String status;
    private  boolean complete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedId() {
        return pedId;
    }

    public void setPedId(int pedId) {
        this.pedId = pedId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
