package hieu.springbootecommerceultimate.service;

public interface EmailService {
    void sendEmail(String recipient, String subject, String body);
}
