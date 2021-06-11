package main.model;

import javafx.beans.property.SimpleStringProperty;

public class booking {

    private SimpleStringProperty date;
    private SimpleStringProperty table_id;
    private SimpleStringProperty status;
    private SimpleStringProperty user_id;
    private SimpleStringProperty booking_id;


    public booking(String date, String table_id, String status) {
        this.date = new SimpleStringProperty(date);
        this.table_id = new SimpleStringProperty(table_id);
        this.status = new SimpleStringProperty(status);
    }

    public booking(String date, String table_id, String status, String user_id, String booking_id){
        this.date = new SimpleStringProperty(date);
        this.table_id = new SimpleStringProperty(table_id);
        this.status = new SimpleStringProperty(status);
        this.user_id = new SimpleStringProperty(user_id);
        this.booking_id = new SimpleStringProperty(booking_id);
    }

    public String getUser_id() { return user_id.get(); }

    public SimpleStringProperty user_idProperty() { return user_id; }

    public void setUser_id(String user_id) { this.user_id.set(user_id); }

    public String getBooking_id() { return booking_id.get(); }

    public SimpleStringProperty booking_idProperty() { return booking_id; }

    public void setBooking_id(String booking_id) { this.booking_id.set(booking_id); }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTable_id() {
        return table_id.get();
    }

    public SimpleStringProperty table_idProperty() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id.set(table_id);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
