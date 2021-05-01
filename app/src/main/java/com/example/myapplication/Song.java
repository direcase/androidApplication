package com.example.myapplication;

public class Song {
    long id;
    String txtName;

    public Song(long id, String txtName) {
        this.id = id;
        this.txtName = txtName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }
}
