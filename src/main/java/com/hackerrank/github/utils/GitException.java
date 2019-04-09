package com.hackerrank.github.utils;

public class GitException extends Exception {
    private static final long serialVersionUID = 5652891496418080845L;

    protected GitException() {
    }

    public GitException(String errorMsg) {
        super(errorMsg);
    }
}