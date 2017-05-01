/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.questionable.utility;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author viseshprasad
 */
public class MailUtils {
    public static void sendMail(String to, String from, String subject, String body, boolean bodyIsHTML) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", "smtp.gmail.com");
        props.put("mail.smtps.port", 465);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        Message message = new MimeMessage(session);
        message.setSubject(subject);
        if (bodyIsHTML) {
            message.setContent(body, "text/html");
        } else {
            message.setText(body);
        }
        Address fromAddress = new InternetAddress(from);
        Address toAddress = new InternetAddress(to);
        message.setFrom(fromAddress);
        message.setRecipient(Message.RecipientType.TO, toAddress);
        Transport transport = session.getTransport();
        transport.connect("viseshdeals227@gmail.com", "Viseshdeals@227");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
