package com.example.demo.JavaConcurrency.Thread.createAndRunThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyThreadOfRunnable2 implements Runnable{
    private String name;

    public MyThreadOfRunnable2(String name){
        this.name = name;
    }

    @Override
    public void run() {
        log.info("name:" + name);
    }
}
