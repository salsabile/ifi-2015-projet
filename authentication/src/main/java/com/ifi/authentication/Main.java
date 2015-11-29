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
                repository.save(new User("lshaffer", "Lars", "Shaffer", "EBT77DQU7ZH"));
                repository.save(new User("jcastillo", "Jack", "Castillo", "BOT53LLA4RN"));
                repository.save(new User("slewis", "Signe", "Lewis", "FMQ62SUS4JH"));
                repository.save(new User("kguzman", "Kermit", "Guzman", "DXH32ZNK2OA"));
                repository.save(new User("jchandler", "Joelle", "Chandler", "VSD23DBO9FL"));
                repository.save(new User("evillarreal", "Eagan", "Villarreal", "QXG47OMO5YW"));
                repository.save(new User("vrose", "Vaughan", "Rose", "XML35OCU6JL"));
                repository.save(new User("rwooten", "Raya", "Wooten", "ROY07NKK6YE"));
                repository.save(new User("pfrederick", "Plato", "Frederick", "LUN29ITJ1MX"));
                repository.save(new User("rnewton", "Rajah", "Newton", "BFR50ZOZ1PH"));
            }
        };
    }
}
