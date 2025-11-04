package com.healthmate.app.repositories;

import com.healthmate.app.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
}
