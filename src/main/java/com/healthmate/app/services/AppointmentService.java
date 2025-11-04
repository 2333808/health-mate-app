package com.healthmate.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthmate.app.exceptions.ResourceNotFoundException;
import com.healthmate.app.models.Appointment;
import com.healthmate.app.models.Appointment.Status;
import com.healthmate.app.models.Patient;
import com.healthmate.app.models.Doctor;
import com.healthmate.app.dto.AppointmentRequest;
import com.healthmate.app.repositories.AppointmentRepository;
import com.healthmate.app.repositories.PatientRepository;
import com.healthmate.app.repositories.DoctorRepository;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    // Create appointment using patientId and doctorId
    public Appointment createAppointmentFromIds(AppointmentRequest request) {
        Patient patient = patientRepository.findById(request.patientId)
            .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + request.patientId + " not found"));

        Doctor doctor = doctorRepository.findById(request.doctorId)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor with ID " + request.doctorId + " not found"));

        Appointment appointment = new Appointment(
            patient,
            request.appointmentDate,
            request.appointmentTime,
            doctor,
            Status.valueOf(request.status)
        );

        return appointmentRepository.save(appointment);
    }

    // Update appointment using patientId and doctorId
    public Appointment updateAppointmentFromIds(Long id, AppointmentRequest request) {
        Appointment appointment = appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment with ID " + id + " not found"));

        Patient patient = patientRepository.findById(request.patientId)
            .orElseThrow(() -> new ResourceNotFoundException("Patient with ID " + request.patientId + " not found"));

        Doctor doctor = doctorRepository.findById(request.doctorId)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor with ID " + request.doctorId + " not found"));

        appointment.setPatient(patient);
        appointment.setAppointmentDate(request.appointmentDate);
        appointment.setAppointmentTime(request.appointmentTime);
        appointment.setDoctor(doctor);
        appointment.setStatus(Status.valueOf(request.status));

        return appointmentRepository.save(appointment);
    }

    // Cancel appointment
    public Appointment cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment with ID " + id + " not found"));

        appointment.setStatus(Status.CANCELLED);
        return appointmentRepository.save(appointment);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Filter appointments by status and/or date
    public List<Appointment> filterAppointments(String status, String date) {
        if (status != null && date != null) {
            return appointmentRepository.findByStatusAndAppointmentDate(Status.valueOf(status), date);
        } else if (status != null) {
            return appointmentRepository.findByStatus(Status.valueOf(status));
        } else if (date != null) {
            return appointmentRepository.findByAppointmentDate(date);
        } else {
            return appointmentRepository.findAll();
        }
    }
}
