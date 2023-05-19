package com.techsonnet.contorller;

import com.techsonnet.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;

@RestController
@RequestMapping("/test")
public class RestTestHandler {

    @Autowired
    private WebClient webClient;

    @RequestMapping("/findAll")
    public Collection<Student> findAll(){
        return webClient.get()                                   // 表示发送 get 请求
              .uri("http://localhost:8010/student/findAll")   // 设置 url
              .retrieve()                                        // 实际发起请求并获得响应
              .bodyToFlux(Student.class)
              .collectList()                                     // 阻塞当前线程，等待 Mono<List<Student>> 完成并返回最终的结果
              .block();
    }
}
