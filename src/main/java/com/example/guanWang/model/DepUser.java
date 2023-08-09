package com.example.guanWang.model;

import java.io.Serializable;

public class DepUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickName;

    private String phoneNumber;

    private String address;

    private String duty;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Override
    public String toString() {
        return "DepUser{" +
                "nickName='" + nickName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", duty='" + duty + '\'' +
                '}';
    }
}
