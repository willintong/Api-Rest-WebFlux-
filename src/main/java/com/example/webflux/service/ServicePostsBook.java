package com.example.webflux.service;

import com.example.webflux.entity.PostsBook;
import com.example.webflux.gateway.IPostsBookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicePostsBook implements IPostsBookRepository {

    private final WebClient webClient;

    public ServicePostsBook(WebClient.Builder webclientbuilder, @Value("${postsBook.baseUrl}") String baseUrl) {
        webClient = webclientbuilder
                .baseUrl(baseUrl)
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
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(PostsBook.class);
    }
}
