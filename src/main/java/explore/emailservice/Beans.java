package explore.emailservice;

import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Log4j2
@Configuration
public class Beans {

    @Bean
    public PasswordAuthentication passwordAuthentication() {
        return new PasswordAuthentication("udaykiran0486@gmail.com", "ndbh phwj amrz brnr");
    }

    @Bean
    public JavaMailSender javaMailSender(PasswordAuthentication authentication) throws MessagingException {
        JavaMailSenderImpl sender = new JavaMailSenderImpl() {
            @Override
            public void testConnection() throws MessagingException {
                log.info("Testing Java Mail Sender connection...");
                super.testConnection();
                log.info("Connection successful :)");
            }
        };
        sender.setUsername(authentication.getUserName());
        sender.setPassword(authentication.getPassword());
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.getJavaMailProperties().put("mail.smtp.starttls.enable", "true");
        sender.testConnection();
        log.info("Java Mail Sender is ready to use...");
        return sender;
    }

}
