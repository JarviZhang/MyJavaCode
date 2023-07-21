package com.example.demo.JavaConcurrency.Thread.createAndRunThread;

import lombok.extern.slf4j.Slf4j;

/*
* 实现Runnable接口并重写run方法
* */
@Slf4j
public class MyThreadOfRunnable implements Runnable{

    @Override
    public void run() {
        log.info("I am MyThreadOfRunnable1");
    }
}
