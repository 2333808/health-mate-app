package com.healthmate.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthmate.app.models.Appointment.Status; 
import com.healthmate.app.models.Appointment;
import com.healthmate.app.repositories.AppointmentRepository;
import java.util.Optional;
import java.util.List;

@Service
public class AppointmentService 
{

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Edit appointment
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Optional<Appointment> existing = appointmentRepository.findById(id);
        if (existing.isPresent()) {
            Appointment appointment = existing.get();
            appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            appointment.setAppointmentTime(updatedAppointment.getAppointmentTime());
            appointment.setDoctorName(updatedAppointment.getDoctorName());
            appointment.setStatus(updatedAppointment.getStatus());
            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment not found");
        }
    }

    // Cancel appointment
    public Appointment cancelAppointment(Long id) {
        Optional<Appointment> existing = appointmentRepository.findById(id);
        if (existing.isPresent()) {
            Appointment appointment = existing.get();
            appointment.setStatus(Status.CANCELLED);
            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment not found");
        }
    }

    public List<Appointment> getAllAppointments() {
    return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
    return appointmentRepository.save(appointment);
    }

}
