package com.example.drivingtest.models;

public class Result {
    private int id;
    private int user_id;
    private int result;

    public Result(int id, int user_id, int result) {
        this.id = id;
        this.user_id = user_id;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
