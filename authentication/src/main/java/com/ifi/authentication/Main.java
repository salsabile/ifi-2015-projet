package com.ifi.authentication;

import com.ifi.authentication.model.User;
import com.ifi.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    public static void main(String... args){
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner loadData(final UserRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                repository.save(new User("jgamba", "Jordann", "Gamba", "jordann"));
                repository.save(new User("cbellart", "Clement", "Bellart", "clement"));
                repository.save(new User("shakimi", "Salsabile", "Hakimi", "salsabile"));
                repository.save(new User("DarkVador", "Anakin", "Skywalker", "anakin"));
                repository.save(new User("Yoda", "Maitre", "Yoda", "maitre"));
            }
        };
    }
}
