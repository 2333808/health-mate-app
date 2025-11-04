package com.healthmate.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private String appointmentDate;
    private String appointmentTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private Status status;

    public enum Status {
    SCHEDULED,
    CANCELLED,
    COMPLETED,
    RESCHEDULED
}

    public Appointment() {}

    public Appointment(Patient patient, String appointmentDate, String appointmentTime, Doctor doctor, Status status) {
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.doctor = doctor;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public String getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(String appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(String appointmentTime) { this.appointmentTime = appointmentTime; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
