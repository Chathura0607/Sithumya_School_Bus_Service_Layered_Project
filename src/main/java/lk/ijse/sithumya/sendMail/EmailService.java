package lk.ijse.sithumya.sendMail;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailService {
    private static final String SENDER_EMAIL = "chathuhiru45@gmail.com";
    private static final String PASSWORD = "vhmz gqaj hmvf mjpa";

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    /*public static void sendBusArrivalEmail(String busId, String arrivalTime) {
        String subject = "Bus Arrival Notification";
        String body = "Bus ID " + busId + " will arrive at " + arrivalTime + ".";

        List<String> guardianEmails = new GuardianRepository().getAllGuardianEmails();

        for (String recipientEmail : guardianEmails) {
            sendEmail(recipientEmail, subject, body);
        }
    }*/

    /*public static void sendBusReturnEmail(String busId, String returnTime) {
        String subject = "Bus Return Notification";
        String body = "Bus ID " + busId + " will return at " + returnTime + ".";

        List<String> guardianEmails = new GuardianRepository().getAllGuardianEmails();

        for (String recipientEmail : guardianEmails) {
            sendEmail(recipientEmail, subject, body);
        }
    }*/

    public static void sendLoginSuccessEmail(String userName, String loginTime) {
        String recipientEmail = "achinipramodhya4@gmail.com";

        String subject = "Login Success Notification";
        String body = "User " + userName + " logged in successfully at " + loginTime + ".";

        sendEmail(recipientEmail, subject, body);
        new Alert(Alert.AlertType.CONFIRMATION, "Email sent successfully to " + recipientEmail).show();
    }

    public static boolean sendCodeByEmail(String verificationCode) {
        String recipientEmail = "achinipramodhya4@gmail.com";

        String subject = "Verification Code";
        String body = "Your verification code is: " + verificationCode;

        return sendEmail(recipientEmail, subject, body);
    }

    private static boolean sendEmail(String recipientEmail, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
