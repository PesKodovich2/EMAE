package com.example.emae;

import javafx.beans.property.SimpleIntegerProperty;
import java.sql.Date;

public class BookingData {
    private int idB;
    private String nameR;
    private Date dz;
    private Date dv;
    private double fullPrice;
    private SimpleIntegerProperty status;
    private String nameK;
    private String surnameK;
    private String idSerList;

    public BookingData(int idB, String nameR, Date dz, Date dv, double fullPrice, SimpleIntegerProperty status, String nameK, String surnameK, String idSerList) {
        this.idB = idB;
        this.nameR = nameR;
        this.dz = dz;
        this.dv = dv;
        this.fullPrice = fullPrice;
        this.status = status;
        this.nameK = nameK;
        this.surnameK = surnameK;
        this.idSerList = idSerList;
    }

    public int getIdB() {
        return idB;
    }
    public String getNameR() {
        return nameR;
    }
    public Date getDz() {
        return dz;
    }
    public Date getDv() {
        return dv;
    }
    public void setDv(Date dv) {
        this.dv = dv;
    }
    public void setIdB(int idB) {
        this.idB = idB;
    }
    public void setNameR(String nameR) {
        this.nameR = nameR;
    }
    public void setDz(Date dz) {
        this.dz = dz;
    }
    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }
    public void setNameK(String nameK) {
        this.nameK = nameK;
    }
    public void setSurnameK(String surnameK) {
        this.surnameK = surnameK;
    }
    public void setIdSerList(String idSerList) {
        this.idSerList = idSerList;
    }
    public double getFullPrice() {
        return fullPrice;
    }
    public int getStatus() {
        return status.get();
    }
    public SimpleIntegerProperty statusProperty() {
        return status;
    }
    public void setStatus(int status) {
        this.status.set(status);
    }
    public String getNameK() {
        return nameK;
    }
    public String getSurnameK() {
        return surnameK;
    }
    public String getIdSerList() {
        return idSerList;
    }
    public String getFullName() {
        return nameK + " " + surnameK;
    }
}