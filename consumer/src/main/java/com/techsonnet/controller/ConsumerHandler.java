package com.techsonnet.controller;

import com.techsonnet.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
@RequestMapping("/consumer")
public class ConsumerHandler {
    @Autowired
    private WebClient webClient;

    @GetMapping("/findAll")
    public Collection<Student> findAll(){
        return webClient.get()
              .uri("http://localhost:8013/p/student/findAll")
              .retrieve()
              .bodyToFlux(Student.class)
              .collectList()
              .block();
    }

    @GetMapping("/findById/{id}")
    public Student findById(@PathVariable("id") Integer id){
        return webClient.get()
             .uri("http://localhost:8010/student/findById/{id}",id)
             .retrieve()
             .bodyToMono(Student.class)
             .block();
    }

    @PostMapping("/save")
    public void save(@RequestBody Student student){

        webClient.post()
              .uri("http://localhost:8010/student/save")
                .body(BodyInserters.fromObject(student))
              .retrieve()
              .bodyToMono(Student.class)
              .block();
    }
}
