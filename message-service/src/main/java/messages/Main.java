package messages;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import messages.model.Message;
import messages.repository.MessageRepository;

@SpringBootApplication
public class Main {

    public static void main(String... args){
        SpringApplication.run(Main.class);
    }

    @Bean
    public CommandLineRunner loadData(final MessageRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                repository.save(new Message("Que la force soit avec toi !", "Yoda"));
                repository.save(new Message("Je suis un futur seigneur Sith..", "DarkVador"));
            }
        };
    }
    
}
