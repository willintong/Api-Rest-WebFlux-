package com.example.webflux.entity;

import lombok.*;

@Value
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class PostsBook {
    Integer id;
    Integer userId;
    String title;
    String body;
}
