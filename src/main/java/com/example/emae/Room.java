package com.example.emae;

public class Room {
    private String photo;
    private String description;
    private double price;
    private int idR;
    private String nameR;

    public Room(String photo, String description, double price, int idR, String nameR) {
        this.photo = photo;
        this.description = description;
        this.price = price;
        this.idR = idR;
        this.nameR = nameR;
    }
    public int getIdR() {
        return idR;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getNameR() {
        return nameR;
    }
    public void setNameR(String nameR) {
        this.nameR = nameR;
    }
}
