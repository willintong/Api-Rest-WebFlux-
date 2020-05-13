package com.example.webflux.controller;

import com.example.webflux.entity.PostsBook;
import com.example.webflux.gateway.IPostsBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import static org.mockito.Mockito.when;

@WebFluxTest(ControllerPostsBook.class)
public class ControllerPostsBookTest {

    @MockBean
    private IPostsBookRepository iPostsBookRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnFromRepository(){
        PostsBook postsBook = new PostsBook(1,2,"My title","Cuerpo");

        when(iPostsBookRepository.findAll())
                .thenReturn(Flux.just(postsBook));

        webTestClient.get()
                .uri("/posts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PostsBook.class)
                .hasSize(1)
        .contains();
    }

    @Test
    void shouldReturnFromRepositoryById(){
        PostsBook postsBook = new PostsBook(1,2,"My title","Cuerpo");

        when(iPostsBookRepository.findById(1))
                .thenReturn(Mono.just(postsBook));

        webTestClient.get()
                .uri("/posts/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PostsBook.class);
    }
}
