// package com.tgid.teste.component;

// import java.util.Properties;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.JavaMailSenderImpl;

// @Configuration
// public class MensagemService {

//   @Bean
//   public JavaMailSender javaMailSender() {
//     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//     mailSender.setHost("your.mail.server.com");
//     mailSender.setPort(587);
//     mailSender.setUsername("your-email@example.com");
//     mailSender.setPassword("your-email-password");

//     Properties props = mailSender.getJavaMailProperties();
//     props.put("mail.transport.protocol", "smtp");
//     props.put("mail.smtp.auth", "true");
//     props.put("mail.smtp.starttls.enable", "true");
//     props.put("mail.debug", "true");

//     return mailSender;
//   }
// }
