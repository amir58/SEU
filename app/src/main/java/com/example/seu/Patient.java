package com.example.seu;

import java.io.Serializable;

public class Patient implements Serializable {

    private String id, name, bloodType, unitType, unitCount, donorsType;

    public Patient() {
    }

    public Patient(String id, String name, String bloodType, String unitType, String unitCount, String donorsType) {
        this.id = id;
        this.name = name;
        this.bloodType = bloodType;
        this.unitType = unitType;
        this.unitCount = unitCount;
        this.donorsType = donorsType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(String unitCount) {
        this.unitCount = unitCount;
    }

    public String getDonorsType() {
        return donorsType;
    }

    public void setDonorsType(String donorsType) {
        this.donorsType = donorsType;
    }
}
