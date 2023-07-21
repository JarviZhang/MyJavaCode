package com.example.demo.JavaConcurrency.designPattern.TwoPhaseTermination;

import lombok.extern.slf4j.Slf4j;

/*
* 两阶段停止设计模式
* 这里使用停止监控程序的例子
* */
@Slf4j
public class TwoPhaseTermination {
    //监控程序线程
    private Thread monitor;

    //启动监控程序
    public void start(){
        monitor = new Thread(() -> {
            while (true){
                Thread current = Thread.currentThread();
                if (current.isInterrupted()){
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    log.debug("执行监控日志");
                } catch (InterruptedException e){
                    e.printStackTrace();
                    //重新设置打断标记
                    current.interrupt();
                }
            }
        });
        monitor.start();
    }

    //停止监控线程
    public void stop(){
        monitor.interrupt();
    }
}
