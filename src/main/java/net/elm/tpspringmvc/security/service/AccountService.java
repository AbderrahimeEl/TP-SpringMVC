package net.elm.tpspringmvc.security.service;

import net.elm.tpspringmvc.security.entities.AppRole;
import net.elm.tpspringmvc.security.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(String username,String Password, String email,String confirmPassword);
    AppRole addNewRole(String role);
    void addRoleToUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    AppUser loadUserByUsername(String username);
}
