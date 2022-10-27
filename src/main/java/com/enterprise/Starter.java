package com.enterprise;

import com.enterprise.rental.exception.DataException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static com.enterprise.rental.utils.Mail.sendEmailWithAttachments;

public class Starter {
    private static final Log log = LogFactory.getLog(Starter.class);
    /**
     * Test sending e-mail with attachments
     */
    public static void main(String[] args) throws Exception {
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "pasha-fghjkl11@i.ua";
        String password = "pasha-fghjkl11pasha-fghjkl11";

        // message info
        String mailTo = "your-friend-email";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";

        // attachments
        String[] attachFiles = new String[2];
        attachFiles[0] = "d:/letter.pdf";

        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                    subject, message, attachFiles);
            log.info("Email sent.");
        } catch (Exception ex) {
            throw new DataException("\"Could not send email.\"" + ex.getMessage());
        }
    }
}
