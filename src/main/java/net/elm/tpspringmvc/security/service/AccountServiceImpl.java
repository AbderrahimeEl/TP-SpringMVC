package net.elm.tpspringmvc.security.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import net.elm.tpspringmvc.security.entities.AppRole;
import net.elm.tpspringmvc.security.entities.AppUser;
import net.elm.tpspringmvc.security.repository.AppRoleRepository;
import net.elm.tpspringmvc.security.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.server.UID;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor @Builder
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser!=null) throw new RuntimeException(" the user" + username +"already exists");
        if (!password.equals(confirmPassword)) throw new RuntimeException("password not match");

        AppUser user = AppUser.builder()
                .email(email)
                .username(username)
                .id(UUID.randomUUID().toString())
                .password(passwordEncoder.encode(password))
                .build();
        AppUser savedUser =  appUserRepository.save(user);
        return savedUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole != null) throw new RuntimeException(" the role" + role + " is already exists");
        appRole = AppRole.builder().role(role).build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole == null) throw new RuntimeException(" the role" + role + " does not exist");
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException(" the user" + username +"does not exist");
        appUser.getRoles().add(appRole);
//        appUserRepository.save(appUser); you dont neeed to because it is transactional
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppRole appRole = appRoleRepository.findById(role).orElse(null);
        if (appRole == null) throw new RuntimeException(" the role" + role + " does not exist");
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser==null) throw new RuntimeException(" the user" + username +"does not exist");
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
