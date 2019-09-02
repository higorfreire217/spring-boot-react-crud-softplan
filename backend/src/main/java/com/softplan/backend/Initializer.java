package com.softplan.backend;

import com.softplan.backend.model.ProcessRepository;
import com.softplan.backend.model.User;
import com.softplan.backend.model.Process;
import com.softplan.backend.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Stream;

@Component
class Initializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProcessRepository processRepository;

    public Initializer(UserRepository userRepository, ProcessRepository processRepository) {
        this.userRepository = userRepository;
        this.processRepository = processRepository;
    }

    @Override
    public void run(String... strings) {

        Stream.of("Elias", "Fernando", "Dinho",
                "Clevland").forEach(name ->
            userRepository.save(new User(name))
        );
        User elias = userRepository.findByName("Elias").get(0);
        Process processOne = new Process();
        processOne.setDate(new Date());
        processOne.setCity("São Paulo");
        processOne.setStateOrProvince("SP");
        processOne.setPending(true);
        processOne.setDescription("Full Stack Reactive");
        processOne.setUsers(Collections.singleton(elias));
        processRepository.save(processOne);

        userRepository.findAll().forEach(System.out::println);
    }
}

