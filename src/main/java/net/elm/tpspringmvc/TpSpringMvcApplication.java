package net.elm.tpspringmvc;

import net.elm.tpspringmvc.entities.Patient;
import net.elm.tpspringmvc.repository.PatientRepository;
import net.elm.tpspringmvc.security.service.AccountService;
import net.elm.tpspringmvc.security.service.AccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import java.util.List;

@SpringBootApplication
public class TpSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpSpringMvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(new Patient(null, "Ahmed", "yru", 124, false));
            patientRepository.save(new Patient(null, "Mohammed", "yru", 124, false));
            patientRepository.save(new Patient(null, "Imane", "yru", 124, false));
            patientRepository.save(new Patient(null, "kawthar", "yru", 124, false));
            patientRepository.save(new Patient(null, "Ahmed", "yru", 124, false));
            patientRepository.save(new Patient(null, "Mohammed", "yru", 124, false));
            patientRepository.save(new Patient(null, "Imane", "yru", 124, false));
            patientRepository.save(new Patient(null, "kawthar", "yru", 124, false));
            List<Patient> patients = patientRepository.findAll();
            patients.forEach(p -> {
                System.out.println(p.toString());
            });
        };
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
//        PasswordEncoder passwordEncoder = passwordEncoder();
//        return args -> {
//            jdbcUserDetailsManager.createUser(
//                    User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build());
//            jdbcUserDetailsManager.createUser(
//                    User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build());
//            jdbcUserDetailsManager.createUser(
//                    User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build());
//        };
//    }

    @Bean
    public CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
        return args -> {
            accountService.addNewRole("ADMIN");
            accountService.addNewRole("USER");

            accountService.addNewUser("user1","1234","user1@gmail.com","1234");
            accountService.addNewUser("user2","1234","user2@gmail.com","1234");
            accountService.addNewUser("admin1","1234","admin1@gmail.com","1234");

            accountService.addRoleToUser("admin1","ADMIN");
            accountService.addRoleToUser("admin1","USER");
            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");

        };
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
