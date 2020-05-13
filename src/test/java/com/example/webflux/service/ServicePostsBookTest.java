package com.example.webflux.service;

import com.example.webflux.entity.PostsBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class ServicePostsBookTest {

    @Test
    void shouldFetchPostsFromExternalService() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PostsBook postsBook = new PostsBook(1,2,"My title","Cuerpo");
        String body = objectMapper.writeValueAsString(postsBook);

        WebClient.Builder webClientBuilder = WebClient.builder()
                .exchangeFunction(response ->{
                    return Mono.just(ClientResponse.create(HttpStatus.OK)
                            .header("content-type", "application/json")
                            .body(body)
                            .build());
                });
        ServicePostsBook servicePostsBook = new ServicePostsBook(webClientBuilder);
        StepVerifier.create(servicePostsBook.findAll())
        .expectNext(postsBook)
        .verifyComplete();
    }
    @Test
    void shouldFetchPostsFromExternalServiceById() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PostsBook postsBook = new PostsBook(1,2,"My title","Cuerpo");
        String body = objectMapper.writeValueAsString(postsBook);

        WebClient.Builder webClientBuilder = WebClient.builder()
                .exchangeFunction(response ->{
                    return Mono.just(ClientResponse.create(HttpStatus.OK)
                            .header("content-type", "application/json")
                            .body(body)
                            .build());
                });
        ServicePostsBook servicePostsBook = new ServicePostsBook(webClientBuilder);
        StepVerifier.create(servicePostsBook.findById(2))
                .expectNext(postsBook)
                .verifyComplete();
    }

}
