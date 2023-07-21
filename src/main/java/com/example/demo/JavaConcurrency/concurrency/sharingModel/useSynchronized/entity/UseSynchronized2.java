package com.example.demo.JavaConcurrency.concurrency.sharingModel.useSynchronized.entity;

import com.example.demo.JavaConcurrency.concurrency.sharingModel.CriticalSectionQuestion;
import com.example.demo.JavaConcurrency.concurrency.sharingModel.useSynchronized.entity.Room;
import lombok.extern.slf4j.Slf4j;

/*
* 这里进一步将要保护的共享变量放在包装类中
* */
@Slf4j
public class UseSynchronized2 extends CriticalSectionQuestion {
    Room room = new Room();

    @Override
    public void startThread1() {
        t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++){
                room.increment();
            }
        });
        t1.start();
    }

    @Override
    public void startThread2() {
        t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++){
                room.decreatment();
            }
        });
        t2.start();
    }

    @Override
    public void getCount() throws InterruptedException {
        while (t1 == null || t2 == null){
            Thread.sleep(1000);
        }
        t1.join();
        t2.join();
        log.debug(Integer.toString(room.get()));
    }
}
