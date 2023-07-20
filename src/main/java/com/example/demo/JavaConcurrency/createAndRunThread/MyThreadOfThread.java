package com.example.demo.JavaConcurrency.createAndRunThread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class MyThreadOfThread extends Thread{
    @Override
    public void run() {
        log.info("I am MyThreadOfThread1");
    }
}
