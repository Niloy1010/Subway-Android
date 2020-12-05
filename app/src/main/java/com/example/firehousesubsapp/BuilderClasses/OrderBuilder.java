package com.example.firehousesubsapp.BuilderClasses;

import com.example.firehousesubsapp.Pojo.Order;

public class OrderBuilder {

    private int id;


    private String name="";
    private String mayo="";
    private String lettuce="";
    private String tomato="";
    private String onion="";

    private String mustard="";
    private String BBQ="";
    private String ranch="";
    private String bacon="";

    private String small;

    public void setSmall(String small) {
        this.small = small;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSizeSelected(String sizeSelected) {
        this.sizeSelected = sizeSelected;
    }

    public void setBreadSelected(String breadSelected) {
        this.breadSelected = breadSelected;
    }

    private String medium ;
    private String large;
    private String cheese;
    private boolean white;
    private boolean wheat;
    private int quantity;
    private String sizeSelected;
    private String breadSelected;
    String price;
    private  String chips;

    public void setPrice(String price) {
        this.price = price;
    }

    public void setChips(String chips) {
        this.chips = chips;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    private  String cookie;
    private  String drinks;


    public Order build(){
        return new Order(id, name, mayo, lettuce, tomato, onion, mustard, BBQ, ranch,bacon, small, medium, large, cheese, white, wheat,quantity,sizeSelected,breadSelected, price,chips,cookie,drinks);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMayo(String mayo) {
        this.mayo = mayo;
    }

    public void setLettuce(String lettuce) {
        this.lettuce = lettuce;
    }

    public void setTomato(String tomato) {
        this.tomato = tomato;
    }

    public void setOnion(String onion) {
        this.onion = onion;
    }

    public void setMustard(String mustard) {
        this.mustard = mustard;
    }

    public void setBBQ(String BBQ) {
        this.BBQ = BBQ;
    }

    public void setRanch(String ranch) {
        this.ranch = ranch;
    }

    public void setBacon(String bacon) {
        this.bacon = bacon;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public void setWheat(boolean wheat) {
        this.wheat = wheat;
    }




}
