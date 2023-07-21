package com.example.demo.JavaConcurrency.thread.createAndRunThread;

import com.example.demo.JavaConcurrency.Thread.createAndRunThread.MyThreadOfCallable;
import com.example.demo.JavaConcurrency.Thread.createAndRunThread.MyThreadOfRunnable;
import com.example.demo.JavaConcurrency.Thread.createAndRunThread.MyThreadOfRunnable2;
import com.example.demo.JavaConcurrency.Thread.createAndRunThread.MyThreadOfThread;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
//创建和运行线程的方式
public class CreateAndRunThreadTest {

    //继承Thread并重写run方式
    @Test
    public void test1(){
        //创建线程的方式
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

    //implement Runnable interface and override run function
    /*
    * 优点：
    * 1. 便于传递参数，可以直接使用实例方法或者构造方法传递参数
    * 2. 相对于继承thread类的方式，可以继承其他类
    * */
    @Test
    public void test2(){
        //创建线程的方式
        MyThreadOfRunnable runnable1 = new MyThreadOfRunnable();
        Thread thread1 = new Thread(runnable1,"T1");
        thread1.start();
        //利用一个Runnable创建按另一个具有相同run方法的线程
        Thread thread2 = new Thread(runnable1,"T2");
        thread2.start();
        //使用匿名内部类方式实现
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                log.info("I am MyThreadOfRunnable2");
            }
        };
        Thread thread3 = new Thread(runnable2,"T3");
        thread3.start();
        //使用lamda精简代码
        Runnable runnable3 = () -> log.info("I am runnable3 use lambda");
        Thread thread4 = new Thread(runnable3, "T4");
        thread4.start();
        //传递变量的例子
        new Thread(new MyThreadOfRunnable2("Tom"),"T5").start();
    }

    //implement Callable interface and use FutureTask to create thread
    /*
    * 优点：可以返回数据
    * */
    @Test
    public void test3() throws InterruptedException{
        //create thread
        FutureTask<String> futureTask = new FutureTask<>(new MyThreadOfCallable());
        //start thread
        new Thread(futureTask).start();
        //get return result
        try {
            String res = futureTask.get();
            log.info("return res : " + res);
        } catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
