package explore.emailservice;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
@RequiredArgsConstructor
public class EmailServiceApplication {

    private final Utils utils;
    private final JavaMailSender sender;
    private final PasswordAuthentication authentication;

    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void execute() throws MessagingException {
        System.out.println("Hello.....");
        MimeMessage message = sender.createMimeMessage();

        message.setSubject("Java Developer Application");
        message.setFrom(authentication.getUserName());
        message.addRecipients(Message.RecipientType.TO, authentication.getUserName());

        MimeBodyPart textPart = new MimeBodyPart();
        MimeBodyPart attachmentPart = new MimeBodyPart();

        utils.textBody(textPart);
        utils.attachResume(attachmentPart);

        Multipart multipart = new MimeMultipart(textPart, attachmentPart);
        message.setContent(multipart);

        System.out.println("Sending Mail ...");
        sender.send(message);
        System.out.println("Sent successfully");

    }

}
