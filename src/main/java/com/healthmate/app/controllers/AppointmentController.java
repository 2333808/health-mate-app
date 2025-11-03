package com.healthmate.app.controllers;

import com.healthmate.app.models.Appointment;
import com.healthmate.app.services.AppointmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController 
{
    @Autowired
    private AppointmentService appointmentService;

    // Edit appointment
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment updatedAppointment) {
        Appointment result = appointmentService.updateAppointment(id, updatedAppointment);
        return ResponseEntity.ok(result);
    }

    // Cancel appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id) {
        Appointment result = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
    return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
    return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }

}
