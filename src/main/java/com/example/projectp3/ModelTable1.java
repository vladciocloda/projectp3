package com.example.projectp3;

///////////////////////// ModelTable for teachers /////////////////////////
public class ModelTable1 {
    String id,name,course;

    public ModelTable1(String id, String name, String course){
        this.id = id;
        this.name = name;
        this.course = course;
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
}
