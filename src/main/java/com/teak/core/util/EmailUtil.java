package com.teak.core.util;

import com.teak.core.pojo.EmailPogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 柚mingle木
 * @version 1.0
 * @date 2023/1/26
 */
@Component
public class EmailUtil {

    private static EmailPogo emailPogo;

    private static JavaMailSender javaMailSender;

    @Autowired
    public void setEmailPogo(EmailPogo emailPogo) {
        EmailUtil.emailPogo = emailPogo;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        EmailUtil.javaMailSender = javaMailSender;
    }

    public void sendMessage(String receiver, String messageSubject, String messageContent) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailPogo.getSendfrom()); //设置发送方
        simpleMailMessage.setTo(receiver); //接收方
        simpleMailMessage.setSubject(messageSubject); //发送主题
        simpleMailMessage.setText(messageContent);  //发送内容
        simpleMailMessage.setSentDate(new Date()); //设置发送时间

        javaMailSender.send(simpleMailMessage);

    }
}
