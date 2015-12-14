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
                repository.save(new Userprofil("a", "email@email.fr", "facebook", "twitter", "linkedinid", "Aucune", "Aucun"));
                repository.save(new Userprofil("jgamba", "jgamba@email.fr", "jgambaF", "jgambaT", "jgambaL", "JEE", "Réseau social"));
                repository.save(new Userprofil("cbellart", "cbellart@email.fr", "cbellartF", "", "", "JEE", "Réseau social"));
                repository.save(new Userprofil("shakimi", "shakimi@email.fr", "shakimiF", "shakimiT", "", "JEE", "Réseau social"));
                repository.save(new Userprofil("Yoda", "yoda_jedi@force.inter", "yodaF", "yodaT", "", "Force", "Meilleur Jedi"));
                repository.save(new Userprofil("DarkVador", "anakin_jedisith@forceobscur.inter", "anakinF", "anakinTT", "", "Force obscur", "Enfant prodige"));
            }
        };
    }
    
}
