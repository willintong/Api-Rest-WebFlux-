package com.example.webflux.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsBook {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
