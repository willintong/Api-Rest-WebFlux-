package com.example.webflux.controller;

import com.example.webflux.entity.PostsBook;
import com.example.webflux.gateway.IPostsBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class ControllerPostsBook {

    @Autowired
    private IPostsBookRepository iPostsBookRepository;

    @GetMapping
    public Flux<PostsBook> findAll(){
        return iPostsBookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<PostsBook> findById(@PathVariable Integer id){
        return iPostsBookRepository.findById(id);
    }
}
