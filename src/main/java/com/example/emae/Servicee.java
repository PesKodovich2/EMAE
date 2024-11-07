package com.example.emae;

public class Servicee {
    private String name;
    private double price;
    private int idS;

    public Servicee(String name, double price, int idS) {
        this.name = name;
        this.price = price;
        this.idS = idS;
    }
    public String getName() {
        return name;
    }
    public int getIdS() {
        return idS;
    }
    public double getPrice() {
        return price;
    }
}

