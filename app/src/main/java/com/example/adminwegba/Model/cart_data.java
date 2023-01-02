package com.example.adminwegba.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class cart_data implements Serializable {
    ArrayList<meal> meals = new ArrayList<>();
    ArrayList<meal_order> meals_order = new ArrayList<>();
    private double cart_total;
    private double delivery_fee;
    private double total;
    private String state;
    private String restaurant_name;
    private String time;
    private String gate;
    private String id;
    private String note;
    private String date ;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public ArrayList<meal> getMeals() {
        return meals;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public ArrayList<meal_order> getMeals_order() {
        return meals_order;
    }

    public void setMeals_order(ArrayList<meal_order> meals_order) {
        this.meals_order = meals_order;
    }

    public void setCart_total(double cart_total) {
        this.cart_total = cart_total;
    }

    public void setDelivery_fee(double delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public cart_data(ArrayList<meal> meals) {
        this.meals = meals;
    }

    public cart_data( ) {

    }


    public double getCart_totalexist() {
        return cart_total;
    }
    public double getDelivery_fee() {
        //delivery_fee= Double.parseDouble(restaurants.get(0).getDelivery_fee());
        return delivery_fee;
    }

    public void setMeals(ArrayList<meal> meals) {
        this.meals = meals;
    }


    public double getTotalexist() {
        return total;
    }

    @Override
    public String toString() {
        return "cart_data{" +
                "meals=" + meals +
                ", meals_order=" + meals_order +
                ", cart_total=" + cart_total +
                ", delivery_fee=" + delivery_fee +
                ", total=" + total +
                ", state='" + state + '\'' +
                ", restaurant_name='" + restaurant_name + '\'' +
                ", time='" + time + '\'' +
                ", gate='" + gate + '\'' +
                '}';
    }
}
