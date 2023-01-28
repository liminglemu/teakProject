package com.teak.core.util;

import com.teak.core.pojo.EmailPogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Arrays;
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
    private void setEmailPogo(EmailPogo emailPogo) {
        EmailUtil.emailPogo = emailPogo;
    }

    @Autowired
    private void setJavaMailSender(JavaMailSender javaMailSender) {
        EmailUtil.javaMailSender = javaMailSender;
    }

    public static void sendMessage(String receiver, String messageSubject, String messageContent) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailPogo.getSendfrom()); //设置发送方
        simpleMailMessage.setTo(receiver); //接收方
        simpleMailMessage.setSubject(messageSubject); //发送主题
        simpleMailMessage.setText(messageContent);  //发送内容
        simpleMailMessage.setSentDate(new Date()); //设置发送时间

        javaMailSender.send(simpleMailMessage);
    }

    public static void sendFilesAndMessage(String receiver, String messageSubject, String messageContent, File[] files) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setFrom(emailPogo.getSendfrom());
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setSubject(messageSubject);
            mimeMessageHelper.setText(messageContent);
            mimeMessageHelper.setSentDate(new Date());
            if (files != null && files.length > 0) {
                Arrays.stream(files).forEach(file -> {
                    try {
                        mimeMessageHelper.addAttachment(file.getName(), file);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);
    }
}
