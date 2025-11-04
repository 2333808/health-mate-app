package com.healthmate.app.controllers;

import com.healthmate.app.models.Appointment;
import com.healthmate.app.services.AppointmentService;
import com.healthmate.app.dto.AppointmentRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Create appointment with patientId and doctorId
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequest request) {
        if (request.patientId == null) {
            return ResponseEntity.badRequest().body("Patient ID is required.");
        }

        if (request.appointmentDate == null || !request.appointmentDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return ResponseEntity.badRequest().body("Date must be in YYYY-MM-DD format.");
        }

        if (request.appointmentTime == null || !request.appointmentTime.matches("\\d{2}:\\d{2}")) {
            return ResponseEntity.badRequest().body("Time must be in HH:mm format.");
        }

        if (request.doctorId == null) {
            return ResponseEntity.badRequest().body("Doctor ID is required.");
        }

        if (request.status == null || request.status.isBlank()) {
            return ResponseEntity.badRequest().body("Status is required.");
        }

        Appointment result = appointmentService.createAppointmentFromIds(request);
        return ResponseEntity.ok(result);
    }

    // Edit appointment with patientId and doctorId
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequest request) {
        if (request.patientId == null) {
            return ResponseEntity.badRequest().body("Patient ID is required.");
        }

        if (request.appointmentDate == null || !request.appointmentDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return ResponseEntity.badRequest().body("Date must be in YYYY-MM-DD format.");
        }

        if (request.appointmentTime == null || !request.appointmentTime.matches("\\d{2}:\\d{2}")) {
            return ResponseEntity.badRequest().body("Time must be in HH:mm format.");
        }

        if (request.doctorId == null) {
            return ResponseEntity.badRequest().body("Doctor ID is required.");
        }

        if (request.status == null || request.status.isBlank()) {
            return ResponseEntity.badRequest().body("Status is required.");
        }

        Appointment result = appointmentService.updateAppointmentFromIds(id, request);
        return ResponseEntity.ok(result);
    }

    // Cancel appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id) {
        Appointment result = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(result);
    }

    // Get all appointments
   @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments(
    @RequestParam(required = false) String status,
    @RequestParam(required = false) String date) {
    return ResponseEntity.ok(appointmentService.filterAppointments(status, date));
    }
}