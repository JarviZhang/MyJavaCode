package com.example.demo.otherUtilsPackage.javaMailPackage;

import org.springframework.boot.web.server.MimeMappings;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendTextMail {
    //发件人地址
    private String senderAddress;
    //收件人地址
    private String recipientAddress;
    //发件人账户名
    private String senderAccount;
    //发件人账户密码
    private String senderPassword;
    //发件人SMTP地址
    private String senderHost;
    public SendTextMail(String senderAddress, String recipientAddress, String senderAccount, String senderPassword, String senderHost) {
        this.senderAddress = senderAddress;
        this.recipientAddress = recipientAddress;
        this.senderAccount = senderAccount;
        this.senderPassword = senderPassword;
        this.senderHost = senderHost;
    }

    public void sendMail(String subject,String content) throws Exception{
        //1.链接邮件服务器的参数配置
        Properties properties = new Properties();
        //设置用户的认证方式
        properties.setProperty("mail.smtp.auth", "true");
        //设置传输协议
        properties.setProperty("mail.transport.protocol", "smtp");
        //设置发件人的SMTP服务器地址
        properties.setProperty("mail.smtp.host", senderHost);
        //2.创建定义整个应用程序所需要的环境信息的session对象
        Session session = Session.getInstance(properties);
        //设置调试信息在控制台打印
        session.setDebug(true);
        //3. 创建邮件的实例对象
        Message message = getMimeMessage(session, subject, content);
        //4. 根据session对象获取邮件传输对象Transport
        Transport transport = session.getTransport();
        //设置发件人的账户名和密码
        transport.connect(senderAccount, senderPassword);
        //发送邮件,并发送到所有收件人地址
        //message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message,message.getAllRecipients());
        //如果只想发送给指定的人，可以如下写法
        //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
        //关闭邮件链接
        transport.close();
    }

    //获得创建一封邮件的实例对象
    public MimeMessage getMimeMessage(Session session, String subject, String content) throws Exception{
        //创建一封邮件的实例
        MimeMessage message = new MimeMessage(session);
        //设置发件人地址
        message.setFrom(new InternetAddress(senderAddress));
        /**
         * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
         * MimeMessage.RecipientType.TO:发送
         * MimeMessage.RecipientType.CC：抄送
         * MimeMessage.RecipientType.BCC：密送
         */
        message.setRecipients(MimeMessage.RecipientType.TO, String.valueOf(new InternetAddress(recipientAddress)));
        //设置邮件主题
        message.setSubject(subject);
        //设置邮件正文
        message.setContent(content,"text/html;charset=UTF-8");
        //设置邮件的发送时间
        message.setSentDate(new Date());
        return message;
    }
}
