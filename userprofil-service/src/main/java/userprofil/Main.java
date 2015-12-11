package userprofil;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import userprofil.model.Userprofil;
import userprofil.repository.UserprofilRepository;


@SpringBootApplication
public class Main {

    public static void main(String... args){
        SpringApplication.run(Main.class);
    }

    @Bean
    public CommandLineRunner loadData(final UserprofilRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                repository.save(new Userprofil("a", "email", "facebook", "twitter", "linkedinid"));
            }
        };
    }
    
}
