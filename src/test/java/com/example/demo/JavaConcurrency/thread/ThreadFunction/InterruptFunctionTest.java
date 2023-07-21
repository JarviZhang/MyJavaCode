package com.example.demo.JavaConcurrency.thread.ThreadFunction;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/*
* interrupt方法详解
*
* */
@Slf4j
public class InterruptFunctionTest {
    //打断阻塞的线程
    @Test
    public void test1() throws InterruptedException{
        Thread t = new Thread(() -> {
            log.debug("sleep ...");
            try{
                //打断后会将打断标记置为true
                //如果处于sleep,wait,join的阻塞状态,会抛出InterruptedException异常,打断标记依然位false
                Thread.sleep(5000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }, "t1");
        t.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t.interrupt();
        log.debug("打断标记:{}",t.isInterrupted());
    }
    //打断正常运行的线程
    @Test
    public void test2() throws InterruptedException{
        Thread t = new Thread(() -> {

            while(true){
                boolean interrupted = Thread.currentThread().isInterrupted();
                //这里被打断的线程不会直接被打断
                //而是通过打断标记知道自己被打断了,后续如何处理自己来决定
                if (interrupted){
                    log.info("被打断了");
                    break;
                }
            }
        }, "t1");
        t.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t.interrupt();
    }
}
