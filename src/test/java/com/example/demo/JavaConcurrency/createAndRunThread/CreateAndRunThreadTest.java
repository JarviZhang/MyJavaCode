package com.example.demo.JavaConcurrency.createAndRunThread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
//创建和运行线程的方式
public class CreateAndRunThreadTest {
    //继承Thread并重写run方式
    @Test
    public void test1(){
        MyThreadOfThread thread1 = new MyThreadOfThread();
        thread1.setName("t1");
        thread1.start();
        //可以使用匿名内部类方式简化
        Thread thread2 = new Thread("t2"){
            @Override
            public void run() {
                log.info("I am MyThreadOfThread2");
            }
        };
        thread2.start();
    }
}
