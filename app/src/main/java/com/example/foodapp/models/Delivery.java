package com.example.foodapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Delivery implements Parcelable {
    String id,order_id,from,to,delivered_by,delivered_at,status,created_at,title;

    public Delivery() {
    }



    public Delivery(String id,String order_id,String title, String from, String to, String delivered_by, String delivered_at, String status , String created_at) {
        this.id = id;
        this.order_id=order_id;
        this.title=title;
        this.from = from;
        this.to = to;
        this.delivered_by = delivered_by;
        this.delivered_at = delivered_at;
        this.status = status;
        this.created_at=created_at;
    }

    protected Delivery(Parcel in) {
        id = in.readString();
        order_id = in.readString();
        from = in.readString();
        to = in.readString();
        delivered_by = in.readString();
        delivered_at = in.readString();
        status = in.readString();
        created_at = in.readString();
        title = in.readString();
    }

    public static final Creator<Delivery> CREATOR = new Creator<Delivery>() {
        @Override
        public Delivery createFromParcel(Parcel in) {
            return new Delivery(in);
        }

        @Override
        public Delivery[] newArray(int size) {
            return new Delivery[size];
        }
    };



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(order_id);
        dest.writeString(from);
        dest.writeString(to);
        dest.writeString(delivered_by);
        dest.writeString(delivered_at);
        dest.writeString(status);
        dest.writeString(created_at);
        dest.writeString(title);
    }
}
