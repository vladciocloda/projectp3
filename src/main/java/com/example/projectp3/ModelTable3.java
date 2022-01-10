package com.example.projectp3;

public class ModelTable3 {
    String id,name,course,fee;

    public ModelTable3(String id, String name, String course, String fee){
        this.id = id;
        this.name = name;
        this.course = course;
        this.fee = fee;
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCourse(){
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFee(){
        return fee;
    }
    public void setFee(String fee){
        this.fee = fee;
    }
}
