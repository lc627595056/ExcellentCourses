package com.example.login.pojo;

public class Result {
    private int code;
    private String massage;

    public Result(int code){
        this.code = code;
    }
    public Result(int code,String massage){
        this.code = code;
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
