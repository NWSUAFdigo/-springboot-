package com.hht.lesson3.exception;

public class UserNotFoundException extends RuntimeException {
    private String userName;

    public UserNotFoundException(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
