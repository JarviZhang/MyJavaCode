package com.example.demo.JavaConcurrency.Thread.createAndRunThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class MyThreadOfCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        log.info("I am MyThreadOfCallable");
        return "hello, I am MyThreadOfCallable";
    }
}
