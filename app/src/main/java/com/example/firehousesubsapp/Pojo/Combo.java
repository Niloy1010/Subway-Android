package com.example.firehousesubsapp.Pojo;

public class Combo {
    private String name;
    private String image;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public Combo(String name, String image,String value) {
        this.name = name;
        this.image = image;
        this.value = value;

    }
    public Combo(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
