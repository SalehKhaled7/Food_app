package com.example.foodapp.models;

public class Delivery {
    String id,from,to,delivered_by,delivered_at,status,created_at;

    public Delivery() {
    }

    public Delivery(String id, String from, String to, String delivered_by, String delivered_at, String status ,String created_at) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.delivered_by = delivered_by;
        this.delivered_at = delivered_at;
        this.status = status;
        this.created_at=created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDelivered_by() {
        return delivered_by;
    }

    public void setDelivered_by(String delivered_by) {
        this.delivered_by = delivered_by;
    }

    public String getDelivered_at() {
        return delivered_at;
    }

    public void setDelivered_at(String delivered_at) {
        this.delivered_at = delivered_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
