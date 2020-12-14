package ru.test.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.test.app.repository")
@EntityScan("ru.test.app.entity.*")
@ComponentScan({
        "ru.test.app.*"
})
@EnableSpringDataWebSupport
public class Application {

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
            System.err.println(e);
            System.exit(-1);
        }
    }

}
