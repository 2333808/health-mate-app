package com.healthmate.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.healthmate.app.models.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> 
{    

}
