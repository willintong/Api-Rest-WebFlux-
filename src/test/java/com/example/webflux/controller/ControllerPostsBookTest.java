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


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest(ControllerPostsBook.class)
public class ControllerPostsBookTest {

    @MockBean
    private IPostsBookRepository iPostsBookRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnFromRepository() {
        PostsBook postsBook = PostsGenrator.createPost();

        when(iPostsBookRepository.findAll())
                .thenReturn(Flux.just(postsBook));

        webTestClient.get()
                .uri("/posts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PostsBook.class)
                .hasSize(1)
                .contains(postsBook);

        verify(iPostsBookRepository).findAll();
    }

    @Test
    void shouldReturnFromRepositoryById() {
        PostsBook postsBook = PostsGenrator.createPost();

        when(iPostsBookRepository.findById(postsBook.getId()))
                .thenReturn(Mono.just(postsBook));

        webTestClient.get()
                .uri("/posts/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PostsBook.class)
                .isEqualTo(postsBook);

        verify(iPostsBookRepository).findById(1);
    }
}
