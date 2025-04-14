package net.elm.tpspringmvc.security.repository;

import net.elm.tpspringmvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {

}
