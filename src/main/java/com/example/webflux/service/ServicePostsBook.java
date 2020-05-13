package com.example.webflux.service;

import com.example.webflux.entity.PostsBook;
import com.example.webflux.gateway.IPostsBookRepository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ServicePostsBook implements IPostsBookRepository {

    private final WebClient webClient;

    public ServicePostsBook(WebClient.Builder webclientbuilder){
            webClient = webclientbuilder
                    .baseUrl("https://jsonplaceholder.typicode.com/posts")
                    .build();
    }


    @Override
    public Flux<PostsBook> findAll() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(PostsBook.class);
    }

    @Override
    public Mono<PostsBook> findById(Integer id) {
        return webClient.get()
                .uri("/{id}",id)
                .retrieve()
                .bodyToMono(PostsBook.class);
    }
}
