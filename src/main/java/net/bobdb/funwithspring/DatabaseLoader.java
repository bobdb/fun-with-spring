package net.bobdb.funwithspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {
    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Person("Daemon")));
            log.info("Preloading " + repository.save(new Person("Viserys")));
            log.info("Preloading " + repository.save(new Person("Corlys")));
            };
    }

}
