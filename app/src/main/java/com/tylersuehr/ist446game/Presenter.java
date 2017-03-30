package com.tylersuehr.ist446game;

/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 */
class Presenter<T> {
    private final T view;


    Presenter(T view) {
        this.view = view;
    }

    T getView() {
        return view;
    }
}