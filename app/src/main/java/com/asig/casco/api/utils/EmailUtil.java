package com.asig.casco.api.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String name, String email, String message) throws MessagingException {
        String host = "sandbox.smtp.mailtrap.io";
        String port = "25";
        String username = "0ad97339d10c86";
        String password = "b2b9da86cc1155";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(username));
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("evghenii.sirbu@yahoo.com"));
        mimeMessage.setSubject("Contact Form Submission");
        mimeMessage.setText("Name: " + name + "\nEmail: " + email + "\n\nMessage:\n" + message);

        Transport.send(mimeMessage);
    }
}
