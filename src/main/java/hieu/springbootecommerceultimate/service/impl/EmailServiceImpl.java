//package hieu.springbootecommerceultimate.service.impl;
//
//import hieu.springbootecommerceultimate.service.EmailService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j(topic = "EMAIL-SERVICE")
//@RequiredArgsConstructor
//public class EmailServiceImpl implements EmailService {
//
//    @Value("${sender.email}")
//    private String sender;
//
//    private final JavaMailSender javaMailSender;
//
//
//    @Override
//    public void sendEmail(String recipient, String subject, String body) {
//        SimpleMailMessage mailMessage
//                = new SimpleMailMessage();
//
//        // Setting up necessary details
//        mailMessage.setFrom(sender);
//        mailMessage.setTo(recipient);
//        mailMessage.setText(body);
//        mailMessage.setSubject(subject);
//
//        // Sending the mail
//        javaMailSender.send(mailMessage);
//    }
//}
