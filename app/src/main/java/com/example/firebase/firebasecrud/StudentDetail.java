package com.example.firebase.firebasecrud;

/**
 * Created by amanj on 7/12/2017.
 */

public class StudentDetail  {
    String name;
    String roll;
    String id;
    public StudentDetail(String name, String roll, String id) {
        this.name = name;
        this.roll = roll;
        this.id = id;
    }
    public StudentDetail() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }



    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
