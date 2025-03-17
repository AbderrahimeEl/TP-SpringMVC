package net.elm.tpspringmvc.repository;

import net.elm.tpspringmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient,Long> {
    // first option
    Page<Patient> findByFirstnameContains(String keyword, Pageable pageable);

    //second with HQL
    //@Query("select p from  Patient p where p.firstname like :x")
    //Page<Patient> chercher(@Param("x") String keyword,Pageable pageable);
}
