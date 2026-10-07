package lk.ijse.sithumya.sendMail;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import lk.ijse.sithumya.bo.BOFactory;
import lk.ijse.sithumya.bo.custom.GuardianBO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailService {
    private static final String SENDER_EMAIL = "chathuhiru45@gmail.com";
    private static final String PASSWORD = "vhmz gqaj hmvf mjpa";

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    private static final ExecutorService emailExecutor = Executors.newFixedThreadPool(2, r -> {
        Thread thread = new Thread(r, "EmailService-Worker");
        thread.setDaemon(true);
        return thread;
    });

    private static final GuardianBO guardianBO = (GuardianBO) BOFactory.getBOFactory().getBOType(BOFactory.BOTypes.GUARDIAN);

    public static void sendBusArrivalEmail(String busId, String arrivalTime) {
        String subject = "🚌 Bus Arrival Alert - Sithumya School Bus Service";
        String htmlContent = buildEmailTemplate(
                "School Bus Arrival Notification",
                "Dear Parent / Guardian,",
                "This is to notify you that the school bus is scheduled for morning arrival as detailed below:",
                new String[][]{
                        {"Bus Identifier", busId},
                        {"Scheduled Arrival Time", arrivalTime},
                        {"Status", "On Schedule"}
                },
                "Please make sure your child is at the designated pickup point 5 minutes prior to arrival.",
                "#2563eb"
        );

        CompletableFuture.runAsync(() -> {
            try {
                List<String> guardianEmails = guardianBO.getAllGuardianEmails();
                if (guardianEmails != null) {
                    for (String recipientEmail : guardianEmails) {
                        if (recipientEmail != null && !recipientEmail.trim().isEmpty()) {
                            sendEmail(recipientEmail.trim(), subject, htmlContent);
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Failed to fetch guardian emails: " + e.getMessage());
            }
        }, emailExecutor);
    }

    public static void sendBusReturnEmail(String busId, String returnTime) {
        String subject = "🚌 Bus Return Alert - Sithumya School Bus Service";
        String htmlContent = buildEmailTemplate(
                "School Bus Return Notification",
                "Dear Parent / Guardian,",
                "This is to notify you that the school bus will be returning as detailed below:",
                new String[][]{
                        {"Bus Identifier", busId},
                        {"Scheduled Return Time", returnTime},
                        {"Status", "Departing School"}
                },
                "Please arrange for child pickup at the designated drop-off location on time.",
                "#059669"
        );

        CompletableFuture.runAsync(() -> {
            try {
                List<String> guardianEmails = guardianBO.getAllGuardianEmails();
                if (guardianEmails != null) {
                    for (String recipientEmail : guardianEmails) {
                        if (recipientEmail != null && !recipientEmail.trim().isEmpty()) {
                            sendEmail(recipientEmail.trim(), subject, htmlContent);
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Failed to fetch guardian emails: " + e.getMessage());
            }
        }, emailExecutor);
    }

    public static void sendLoginSuccessEmail(String userName, String loginTime) {
        String recipientEmail = "achinipramodhya4@gmail.com";
        String subject = "🔐 Security Alert: Successful Admin Login";
        String htmlContent = buildEmailTemplate(
                "System Access Notification",
                "Hello Admin,",
                "A successful login to the Sithumya School Bus Service Management System was recorded with the following details:",
                new String[][]{
                        {"Username", userName},
                        {"Login Timestamp", loginTime},
                        {"Security Status", "Authorized"}
                },
                "If you did not perform this login, please change your administrative password immediately.",
                "#4f46e5"
        );

        CompletableFuture.runAsync(() -> sendEmail(recipientEmail, subject, htmlContent), emailExecutor);
    }

    public static boolean sendCodeByEmail(String verificationCode) {
        String recipientEmail = "achinipramodhya4@gmail.com";
        String subject = "🔑 Password Reset Verification Code - Sithumya Transport";
        String htmlContent = "<!DOCTYPE html>" +
                "<html><head><meta charset='UTF-8'></head>" +
                "<body style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica, Arial, sans-serif; background-color: #f8fafc; padding: 24px; margin: 0;'>" +
                "<div style='max-width: 500px; margin: 0 auto; background: #ffffff; border-radius: 12px; border: 1px solid #e2e8f0; overflow: hidden; box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);'>" +
                "<div style='background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); padding: 24px; text-align: center; color: white;'>" +
                "<h2 style='margin: 0; font-size: 20px; font-weight: 700; letter-spacing: 0.5px;'>Sithumya Transport Service</h2>" +
                "<p style='margin: 6px 0 0 0; font-size: 13px; opacity: 0.8;'>Password Reset Request</p>" +
                "</div>" +
                "<div style='padding: 28px; text-align: center; color: #334155;'>" +
                "<p style='font-size: 15px; margin-bottom: 20px;'>Use the one-time verification code below to reset your account password:</p>" +
                "<div style='display: inline-block; background: #f1f5f9; border: 2px dashed #6366f1; border-radius: 8px; padding: 14px 28px; margin: 10px 0 20px 0;'>" +
                "<span style='font-size: 28px; font-weight: 800; letter-spacing: 6px; color: #4338ca; font-family: monospace;'>" + verificationCode + "</span>" +
                "</div>" +
                "<p style='font-size: 13px; color: #64748b; margin: 0;'>This code will expire shortly. If you did not request this code, please ignore this email.</p>" +
                "</div>" +
                "<div style='background-color: #f8fafc; padding: 16px; text-align: center; border-top: 1px solid #f1f5f9; font-size: 12px; color: #94a3b8;'>" +
                "&copy; " + java.time.Year.now().getValue() + " Sithumya School Bus Service. Mapalagama." +
                "</div></div></body></html>";

        return sendEmailSync(recipientEmail, subject, htmlContent);
    }

    private static String buildEmailTemplate(String title, String greeting, String intro, String[][] details, String footerNote, String themeColor) {
        StringBuilder rowsHtml = new StringBuilder();
        for (String[] detail : details) {
            rowsHtml.append("<tr>")
                    .append("<td style='padding: 10px 14px; color: #64748b; font-size: 14px; font-weight: 500; border-bottom: 1px solid #f1f5f9;'>").append(detail[0]).append("</td>")
                    .append("<td style='padding: 10px 14px; color: #0f172a; font-size: 14px; font-weight: 600; text-align: right; border-bottom: 1px solid #f1f5f9;'>").append(detail[1]).append("</td>")
                    .append("</tr>");
        }

        return "<!DOCTYPE html>" +
                "<html><head><meta charset='UTF-8'></head>" +
                "<body style='font-family: -apple-system, BlinkMacSystemFont, Segoe UI, Roboto, Helvetica, Arial, sans-serif; background-color: #f8fafc; padding: 24px; margin: 0;'>" +
                "<div style='max-width: 520px; margin: 0 auto; background: #ffffff; border-radius: 12px; border: 1px solid #e2e8f0; overflow: hidden; box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);'>" +
                "<div style='background: " + themeColor + "; padding: 24px; text-align: center; color: white;'>" +
                "<h2 style='margin: 0; font-size: 20px; font-weight: 700;'>" + title + "</h2>" +
                "<p style='margin: 4px 0 0 0; font-size: 13px; opacity: 0.9;'>Sithumya School Bus Transport Service</p>" +
                "</div>" +
                "<div style='padding: 24px; color: #334155;'>" +
                "<p style='font-size: 15px; font-weight: 600; margin: 0 0 10px 0; color: #0f172a;'>" + greeting + "</p>" +
                "<p style='font-size: 14px; line-height: 1.5; margin: 0 0 16px 0; color: #475569;'>" + intro + "</p>" +
                "<table style='width: 100%; border-collapse: collapse; background: #f8fafc; border-radius: 8px; overflow: hidden; margin-bottom: 16px;'>" +
                rowsHtml.toString() +
                "</table>" +
                "<p style='font-size: 13px; line-height: 1.5; color: #64748b; margin: 0; background: #eff6ff; padding: 12px; border-left: 4px solid " + themeColor + "; border-radius: 4px;'>" + footerNote + "</p>" +
                "</div>" +
                "<div style='background-color: #f8fafc; padding: 16px; text-align: center; border-top: 1px solid #f1f5f9; font-size: 12px; color: #94a3b8;'>" +
                "Sithumya Transport Service &bull; Contact: 0770464448 &bull; Mapalagama" +
                "</div></div></body></html>";
    }

    private static boolean sendEmailSync(String recipientEmail, String subject, String bodyHtml) {
        return sendEmail(recipientEmail, subject, bodyHtml);
    }

    private static boolean sendEmail(String recipientEmail, String subject, String bodyHtml) {
        if (recipientEmail == null || recipientEmail.trim().isEmpty()) {
            return false;
        }

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.connectiontimeout", "10000");
        properties.put("mail.smtp.timeout", "10000");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL, "Sithumya Transport Service"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(bodyHtml, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            System.err.println("Email sending failed for " + recipientEmail + ": " + e.getMessage());
            return false;
        }
    }
}

