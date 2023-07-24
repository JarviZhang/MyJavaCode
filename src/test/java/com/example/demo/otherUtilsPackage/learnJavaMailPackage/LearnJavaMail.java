package com.example.demo.otherUtilsPackage.learnJavaMailPackage;

import com.example.demo.otherUtilsPackage.javaMailPackage.SendTextMail;
import org.junit.jupiter.api.Test;

/*
* 使用Java Mail包实现邮件的接收和发送
* */
public class LearnJavaMail {
    private String senderAddress = "13281834826@163.com";
    private String recipientAddress = "1135470453@qq.com";
    private String senderAccount = "zjw";
    private String senderPassword = "TZCQNFMRMWAYZAZK";
    private String senderHost = "smtp.163.com";
    //发送简单的文本邮件
    @Test
    public void test1(){
        String subject = "hello";
        String content = "this is a test mail by Java Mail jar";
        SendTextMail sendTextMail = new SendTextMail(senderAddress, recipientAddress, senderAccount, senderPassword, senderHost);
        try {
            sendTextMail.sendMail(subject, content);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
