package com.glucoclock.views.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/*
The following code and google gmail apis are gathered in the link below:
https://cloud.google.com/appengine/docs/standard/java/mail/sending-mail-with-mail-api
 */
public class SendMail {
    public static void sendMail(String messagetosend,String emailadd) {
        // patient email get from the database
        String to = emailadd;

        String from = "glucoclock@gmail.com";
        String host = "smtp.gmail.com";
        //connection, configuration, and authentication of the google gmail APIs
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("glucoclock@gmail.com", "IloveJava");//Yes, this is my gmail password :D
            }
        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("alert");
            message.setText(messagetosend); //leave the message here
            Transport.send(message);

            //Add this to the heroku logbook to track all the email sent
            System.out.println("Patient id: Patient email, time, glucose level");
        } catch (MessagingException mex) {
            System.out.println(mex.getMessage());
        }

    }

}