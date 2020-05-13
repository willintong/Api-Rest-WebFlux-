package com.example.webflux.gateway;

import com.example.webflux.entity.PostsBook;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IPostsBookRepository {

    Flux<PostsBook> findAll();
    Mono<PostsBook> findById(Integer id);

}
