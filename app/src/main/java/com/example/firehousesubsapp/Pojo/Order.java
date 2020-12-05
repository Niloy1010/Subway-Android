package com.example.firehousesubsapp.Pojo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "orders")
public class Order implements Serializable {

    @PrimaryKey(autoGenerate = true)
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
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getSizeSelected() {
        return sizeSelected;
    }

    public void setSizeSelected(String sizeSelected) {
        this.sizeSelected = sizeSelected;
    }

    public String getBreadSelected() {
        return breadSelected;
    }

    public void setBreadSelected(String breadSelected) {
        this.breadSelected = breadSelected;
    }

    private String large;
    private String sizeSelected;
    private String breadSelected;
    private String cheese;
    private boolean white;
    private boolean wheat;
    private  String chips;

    public String getChips() {
        return chips;
    }

    public void setChips(String chips) {
        this.chips = chips;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    private  String cookie;
    private  String drinks;
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    int quantity;


    public Order(int id,String name,String mayo,String lettuce,String tomato,String onion,String mustard,String BBQ,String ranch,String bacon,String small,String medium
            ,String large,String cheese,boolean white,boolean wheat,int quantity,String sizeSelected, String breadSelected,String price,String cookie,String chips,String drinks){
        this.id = id;
        this.name = name;
        this.mayo = mayo;
        this.lettuce = lettuce;
        this.tomato = tomato;
        this.onion = onion;
        this.mustard = mustard;
        this.BBQ = BBQ;
        this.bacon = bacon;
        this.ranch = ranch;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.cheese = cheese;
        this.white = white;
        this.wheat = wheat;
        this.quantity =quantity;
        this.sizeSelected = sizeSelected;
        this.breadSelected = breadSelected;
        this.price = price;
        this.drinks = drinks;
        this.chips = chips;
        this.drinks = drinks;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Ignore
    public Order() {

    }


    public Order setNames(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMayo() {
        return mayo;
    }

    public void setMayo(String mayo) {
        this.mayo = mayo;
    }

    public String getLettuce() {
        return lettuce;
    }

    public void setLettuce(String lettuce) {
        this.lettuce = lettuce;
    }

    public String getTomato() {
        return tomato;
    }

    public void setTomato(String tomato) {
        this.tomato = tomato;
    }

    public String getOnion() {
        return onion;
    }

    public void setOnion(String onion) {
        this.onion = onion;
    }

    public String getMustard() {
        return mustard;
    }

    public void setMustard(String mustard) {
        this.mustard = mustard;
    }

    public String getBBQ() {
        return BBQ;
    }

    public void setBBQ(String bbq) {
        this.BBQ = bbq;
    }

    public String getRanch() {
        return ranch;
    }

    public void setRanch(String ranch) {
        this.ranch = ranch;
    }

    public String getBacon() {
        return bacon;
    }

    public void setBacon(String bacon) {
        this.bacon = bacon;
    }


    public String getCheese() {
        return cheese;
    }

    public void setCheese(String cheese) {
        this.cheese = cheese;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isWheat() {
        return wheat;
    }

    public void setWheat(boolean wheat) {
        this.wheat = wheat;
    }

}
