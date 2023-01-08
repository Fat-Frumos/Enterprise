package com.enterprise.rental.service.email;

import com.enterprise.rental.exception.DataException;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * The Mail class describes the functions of a send Email with attachments service.
 *
 * @author Pasha Polyak
 */
public class Mail {
    Mail() {
    }

    /**
     * <p>Assuming you are sending email through rental.enterprise.com to user email
     * Sets SMTP server properties.
     * Creates a new session with an authenticator,
     * a new e-mail message, a message with attachments
     * sets the part as e-mail's content and sends the e-mail
     *
     * @param host        smtp mail gmail property
     * @param port        smtp mail property
     * @param userName    of the user who is sending the email
     * @param password    of the user who is sending the email
     * @param toAddress   e-mail of the registered User
     * @param subject     new email with attachments
     * @param message     to the user in a new email
     * @param attachFiles array of Attached files in pdf format
     * @throws AddressException   The exception thrown when a wrongly formatted address is encountered
     * @throws MessagingException The base class for all exceptions thrown by the Messaging classes
     */
    public static void sendEmailWithAttachments(
            String host, String port,
            final String userName,
            final String password, String toAddress,
            String subject, String message,
            String[] attachFiles)
            throws MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        // creates a new session with an authenticator
        //The class PasswordAuthentication is a data holder that is used by Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    throw new DataException(ex.getMessage());
                }
                multipart.addBodyPart(attachPart);
            }
        }
        // sets the part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);
    }
}
