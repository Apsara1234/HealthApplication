package com.example.healthapplication.Model;

public class HealthBooking {
    private String name;
    private String contact;
    private String gender;
    private String age;
    private String date;
    private String time;

    public HealthBooking(String name, String contact, String gender, String age, String date, String time) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
        this.age = age;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
}
