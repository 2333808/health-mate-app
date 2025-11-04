package com.healthmate.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.healthmate.app.models.Appointment;
import com.healthmate.app.models.Appointment.Status;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByStatus(Status status);

    List<Appointment> findByAppointmentDate(String appointmentDate);

    List<Appointment> findByStatusAndAppointmentDate(Status status, String appointmentDate);
}
