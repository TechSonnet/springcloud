package com.techsonnet.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zipkin")
public class ZipkinHandler {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/port")
    public String getPort() {
        return port;
    }

    @RequestMapping("/error")
    public String handlerError() {
        return "error";
    }
}
