package com.geektech.databaselesson.base.callback;

// Created by askar on 10/9/18.
public interface BaseCallback<T> {
    void onSuccess(T result);

    void onFail(String message);
}
