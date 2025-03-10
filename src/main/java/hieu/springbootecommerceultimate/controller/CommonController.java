//package hieu.springbootecommerceultimate.controller;
//
//import hieu.springbootecommerceultimate.response.ApiResponse;
//import hieu.springbootecommerceultimate.service.EmailService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j(topic = "COMMON-CONTROLLER")
//public class CommonController {
//
//    private final EmailService emailService;
//
//    @PostMapping("/send-mail")
//    public ResponseEntity<ApiResponse> sendMail(@RequestParam String recipient, @RequestParam String subject, @RequestParam String message) {
//        emailService.sendEmail(recipient, subject, message);
//        return ResponseEntity.ok(ApiResponse.builder()
//                        .message("Send email")
//                        .data("Subject: " + subject + "\n + Message:" + message)
//                .build());
//    }
//}
