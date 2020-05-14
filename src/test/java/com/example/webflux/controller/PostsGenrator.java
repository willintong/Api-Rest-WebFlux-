package com.example.webflux.controller;

import com.example.webflux.entity.PostsBook;

public class PostsGenrator {

    public static PostsBook createPost(){
        return PostsBook.builder()
                .id(1)
                .userId(2)
                .title("My title")
                .body("content")
                .build();
    }
}
