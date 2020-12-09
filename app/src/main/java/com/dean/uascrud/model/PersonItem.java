package com.dean.uascrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonItem {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("class")
    @Expose
    private String clas;

    @SerializedName("lesson")
    @Expose
    private String lesson;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("ket")
    @Expose
    private String ket;

    public PersonItem(){

    }

    public PersonItem(int id, String name, String clas, String lesson, String date, String ket){
        this.id = id;
        this.name = name;
        this.clas = clas;
        this.lesson = lesson;
        this.date = date;
        this.ket = ket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKet() {
        return ket;
    }

    public void setKet(String ket) {
        this.ket = ket;
    }
}
