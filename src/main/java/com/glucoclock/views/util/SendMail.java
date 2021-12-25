package com.glucoclock.views.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static void sendMail() {

        String to = "Zimuhuo@outlook.com";
        String from = "glucoclock@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("glucoclock@gmail.com", "IloveJava");

            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("alert");
            message.setText("Dude, what is wrong with your glucose level?");
            Transport.send(message);

            //Add this to the heroku logbook
            System.out.println("Patient id: Patient email, time, glucose level");
        } catch (MessagingException mex) {
            System.out.println(mex.getMessage());
        }

    }

}