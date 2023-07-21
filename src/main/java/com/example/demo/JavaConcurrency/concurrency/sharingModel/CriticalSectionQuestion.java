package com.example.demo.JavaConcurrency.concurrency.sharingModel;

import lombok.extern.slf4j.Slf4j;

/*
* 两个线程同时对共享变量进行读写操作,会导致出现临界区问题
* */
@Slf4j
public class CriticalSectionQuestion {
    protected int count = 0;
    protected Thread t1, t2;

    public void startThread1(){
        t1 = new Thread(() -> {
           for(int i = 0; i < 50000; i++){
               count++;
           }
        }, "t1");
        t1.start();
    }

    public void startThread2(){
        t2 = new Thread(() -> {
            for(int i = 0; i < 50000; i++){
                count--;
            }
        }, "t2");
        t2.start();
    }

    public void getCount() throws InterruptedException {
        while (t1 == null || t2 == null){
            Thread.sleep(1000);
        }
        t1.join();
        t2.join();
        log.debug("count:{}",count);
    }
}
