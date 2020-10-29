package com.example.seu;

import java.io.Serializable;
import java.util.List;

public class DonorData implements Serializable {
    private String id, name, dateOfBirth, nationality, identity, type, date, time;
    private List<Questionnaire> questionnaires;

    public DonorData(String id, String name, String dateOfBirth, String nationality, String identity, String type, String date, String time, List<Questionnaire> questionnaires) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.nationality = nationality;
        this.identity = identity;
        this.type = type;
        this.date = date;
        this.time = time;
        this.questionnaires = questionnaires;
    }

    DonorData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public String getIdentity() {
        return identity;
    }

}
