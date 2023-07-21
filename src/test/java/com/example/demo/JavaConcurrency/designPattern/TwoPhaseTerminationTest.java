package com.example.demo.JavaConcurrency.designPattern;

import com.example.demo.JavaConcurrency.designPattern.TwoPhaseTermination.TwoPhaseTermination;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.nio.file.StandardOpenOption;

/*
* 两阶段提交设计模式
* 目的是优雅的终止线程T2，优雅指的是给T2一个料理后事的方法
* 如果用stop()方法，则会直接杀死线程，线程占用的锁等资源无法释放，从而导致问题
* 这里以监控器举例子,具体流程如图./monitor.png
* */
@Slf4j
public class TwoPhaseTerminationTest {
    @Test
    public void test1() throws InterruptedException{
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        Thread.sleep(3500);
        tpt.stop();
    }
}
