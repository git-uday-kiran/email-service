package explore.emailservice;


import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public final class Utils {

    public void textBody(BodyPart bodyPart) throws MessagingException {
        String bodyText = """
                Hi
                                
                I hope you're having a great week! I have experience with Spring Boot, Hibernate, MySQL, Gradle, Maven, Git, and RESTful API integration from my work as a Software Engineer at Rezo.ai. Coding, problem-solving, and taking pride in writing clean code have always been my passion.
                                
                Responsibilities at Rezo.ai include designing, coding, and testing the REST APIs. Enabling the AI responses for automated call bots and chatbots. Training the AI by processing the user information to optimize user experiences and deliver more intelligent and personalized interactions, Being responsible for project enhancement, change requests, features, and production support.
                                
                My technical skills are Java, Spring Boot, REST APIs, Hibernate, MySQL, Git, Maven, Gradle, JUnit, Microservices, Spring Framework, Multithreading, Linux, API Development, Design Patterns, Eclipse, IntelliJ IDEA.
                                
                Are there any open positions for entry level Java Developer? I am eager to demonstrate my skills and contribute to your team. Find the mentioned resume to get to know more about me. Thank you for your time.
                                
                Regards,
                Uday Kiran Mekala     
                """;
        bodyPart.setText(bodyText);
    }

    public void attachResume(BodyPart bodyPart) throws MessagingException {
        File resume = resume();
        bodyPart.setDataHandler(new DataHandler(new FileDataSource(resume)));
        bodyPart.setFileName(resume.getName());
    }

    public File resume() {
        String path = "/media/uday-kiran/Windows/Users/udayk/OneDrive/Desktop/Uday-Kiran-Mekala.pdf";
        return new File(path);
    }
}
