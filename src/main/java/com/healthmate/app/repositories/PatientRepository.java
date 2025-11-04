package com.healthmate.app.repositories;

import com.healthmate.app.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
