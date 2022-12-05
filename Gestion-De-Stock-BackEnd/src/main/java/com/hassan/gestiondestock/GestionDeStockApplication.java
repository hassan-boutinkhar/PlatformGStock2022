package com.hassan.gestiondestock;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@SpringBootApplication()
@EnableJpaRepositories
@EnableJpaAuditing
public class GestionDeStockApplication {

    public static void main(String[] args) {

        SpringApplication.run(GestionDeStockApplication.class, args);
    }
    @Bean
    public OpenAPI openApiConfig(){
        return new OpenAPI().info(apiInfo());
    }
    public Info apiInfo(){
        Info info=new Info();
        info    .description("Gestion de stock API documentation")
                .title("Gestion de stock REST API")
                .version("v1.0.0");
        return info;


    }
    /*@Bean
    public JavaMailSender getJavaMailSender()
    {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         mailSender.setHost("smtp.gmail.com");
         mailSender.setPort(587);

         mailSender.setUsername("hassanboutinkhar15@gmail.com");
         mailSender.setPassword("ydwbugqdigbmdyzx");

        Properties props = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }*/

}
