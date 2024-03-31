// package com.tgid.teste.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.MailException;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.stereotype.Service;

// @Service
// public class MensagemService {

//   @Autowired
//   private JavaMailSender emailSender;

//   public void enviarEmail(String destinatario, String assunto, String mensagem) {
//     SimpleMailMessage email = new SimpleMailMessage();
//     email.setTo(destinatario);
//     email.setSubject(assunto);
//     email.setText(mensagem);

//     try {
//       emailSender.send(email);
//     } catch (MailException e) {
//       // Trate o erro adequadamente
//       e.printStackTrace();
//     }
//   }

// }
