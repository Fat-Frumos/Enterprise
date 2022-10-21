//package com.enterprise.rental.utils;
//
//import com.enterprise.rental.entity.User;
//import com.enterprise.rental.exception.DataException;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import java.util.Properties;
//
//public class Mail {
//    private static final Log log = LogFactory.getLog(Mail.class);
//    private static final String from = "pasha-fghjkl11@i.ua";
//    private static final String password = "pasha-fghjkl11pasha-fghjkl11";
//    private static final String filename = "/letter.pdf";
//    // Assuming you are sending email through rental.enterprise.com
////    private static final String host = "rental.enterprise.com";
//    private static final String host = "127.0.0.1";
//
//    public boolean send(User user) {
//
//        String to = user.getEmail();
//        // Getting system properties
//        Properties properties = System.getProperties();
//
//        // Setting up mail server
//        properties.setProperty("mail.smtp.host", host);
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.starttls.enable", "true");
//        properties.setProperty("mail.smtp.port", "25");
//
//
//        /**
//         * Creating session object to get properties
//         * Get the session object
//         * */
//        Session session = Session.getDefaultInstance(properties,
//                new javax.mail.Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(from, password);
//                    }
//                });
//        /**
//         * Compose message object
//         * */
//        try {
//            // Create a default MimeMessage object.
//            Message message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse(to));
//
//            // Set Subject: header field
//            message.setSubject("Subject Customer");
//            message.setContent("<h1>Rental Car</h1><b style='color:red;'>bold-red email</b> using JavaMailer","text/html");
//
//            //TODO Multipart multipart
//            Multipart multipart = createMultipart("This is message body");
//
//            // Send the complete message parts and Send message
//            message.setContent(multipart);
//            Transport.send(message);
//            log.info("message sent....");
//            return true;
//        } catch (MessagingException e) {
//            throw new DataException(e);
//        }
//    }
//
//    /**
//     * Create MimeBodyPart object and set the actual message
//     * return multipart message
//     */
//    private Multipart createMultipart(String message) throws MessagingException {
//
//        BodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setText(message);
//
//        // Create a multipart message and Set text message part
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//
//        // Create new MimeBodyPart object and set DataHandler object to this object
//        messageBodyPart = new MimeBodyPart();
//        DataSource source = new FileDataSource(filename);
//        messageBodyPart.setDataHandler(new DataHandler(source));
//        messageBodyPart.setFileName(filename);
//        multipart.addBodyPart(messageBodyPart);
//        return multipart;
//    }
//}