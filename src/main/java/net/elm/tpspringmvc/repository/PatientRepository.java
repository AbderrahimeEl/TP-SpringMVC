package net.elm.tpspringmvc.repository;

import net.elm.tpspringmvc.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient,Long> {
}
