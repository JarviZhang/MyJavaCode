package com.example.demo.JavaConcurrency.concurrency.sharingModule;

import com.example.demo.JavaConcurrency.concurrency.sharingModel.CriticalSectionQuestion;
import com.example.demo.JavaConcurrency.concurrency.sharingModel.useSynchronized.entity.UseSynchronized;
import com.example.demo.JavaConcurrency.concurrency.sharingModel.useSynchronized.entity.UseSynchronized2;
import org.junit.jupiter.api.Test;

/*
* 对于共享变量的读写问题的问题
* */
public class CriticalSectionQuestionTest {
    CriticalSectionQuestion q;

    //两个线程对临界区同时进行不加限制的读写,会导致结果出现问题
    @Test
    public void test1() throws InterruptedException {
        //这里count加减各50000次,但是结果有时候不是0
        testCriticalSectionQuestion(new CriticalSectionQuestion());
    }

    //这里使用synchronized解决
    @Test
    public void test2() throws InterruptedException {
        testCriticalSectionQuestion(new UseSynchronized());
        //将要保护的共享变量放在包装类中
        testCriticalSectionQuestion(new UseSynchronized2());
    }

    private void testCriticalSectionQuestion(CriticalSectionQuestion q) throws InterruptedException {
        q.startThread1();
        q.startThread2();
        q.getCount();
    }
}
