package net.elm.tpspringmvc;

import net.elm.tpspringmvc.entities.Patient;
import net.elm.tpspringmvc.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class TpSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpSpringMvcApplication.class, args);
    }
    @Bean
    public CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Ahmed","yru",24,false));
            patientRepository.save(new Patient(null,"Mohammed","yru",24,false));
            patientRepository.save(new Patient(null,"Imane","yru",24,false));
            patientRepository.save(new Patient(null,"kawthar","yru",24,false));
            List<Patient> patients = patientRepository.findAll();
            patients.forEach(p->{
                System.out.println(p.toString());
            });
        };
    }

}
