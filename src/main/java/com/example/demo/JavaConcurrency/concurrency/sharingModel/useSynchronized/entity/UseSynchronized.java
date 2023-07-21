package com.example.demo.JavaConcurrency.concurrency.sharingModel.useSynchronized.entity;

import com.example.demo.JavaConcurrency.concurrency.sharingModel.CriticalSectionQuestion;

public class UseSynchronized extends CriticalSectionQuestion {
    final Object room = new Object();

    @Override
    public void startThread1() {
        t1 = new Thread(() -> {
           for (int i = 0; i < 50000; i++){
               synchronized (room){
                   count++;
               }
           }
        });
        t1.start();
    }

    @Override
    public void startThread2() {
        t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++){
                synchronized (room){
                    count--;
                }
            }
        });
        t2.start();
    }
}
