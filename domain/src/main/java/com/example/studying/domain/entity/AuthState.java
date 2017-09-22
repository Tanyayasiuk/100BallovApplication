package com.example.studying.domain.entity;


public class AuthState {

    private boolean isSigned;

    public AuthState(boolean isSigned) {
        this.isSigned = isSigned;
    }

    public boolean isSigned() {
        return isSigned;
    }
}
