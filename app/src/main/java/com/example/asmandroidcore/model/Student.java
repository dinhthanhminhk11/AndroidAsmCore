package com.example.asmandroidcore.model;

public class Student {
    private int id;
    private String name;
    private String birth;
    private String phone;
    private String nameClassroom;

    public Student() {
    }

    public Student(int id, String name, String birth, String phone, String nameClassroom) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.phone = phone;
        this.nameClassroom = nameClassroom;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameClassroom() {
        return nameClassroom;
    }

    public void setNameClassroom(String nameClassroom) {
        this.nameClassroom = nameClassroom;
    }

}
