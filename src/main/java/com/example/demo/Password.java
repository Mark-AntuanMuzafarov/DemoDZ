package com.example.demo;

public class Password {
    String password;
    String repeatpassword;

    public Password(String password, String repeatpassword) {
        this.password = password;
        this.repeatpassword = repeatpassword;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatpassword() {
        return repeatpassword;
    }
    public boolean isRight(){
        return password.equals(repeatpassword);
    }
}
