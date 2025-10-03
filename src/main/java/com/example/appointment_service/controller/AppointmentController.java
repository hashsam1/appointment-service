package com.example.appointment_service.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.appointment_service.model.Appointments;

import com.example.appointment_service.service.AppointmentsService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "http://localhost:5173")  // allow React dev server
public class AppointmentController {

    private final AppointmentsService appointmentsService;





    public AppointmentController(AppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;

    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Appointments appointment) {

//how would we know if the patient id is legitamate or not , if the service for both patient id is differnet

        // Create and save the appointment
        Appointments saved = appointmentsService.createAppointment(appointment);
        return ResponseEntity.ok(saved);
    }
    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeAppointment(@PathVariable Long id) {
        try {
            Appointments completed = appointmentsService.completeAppointment(id);
            return ResponseEntity.ok(completed);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping
    public List<Appointments> getAllAppointments() {
        return appointmentsService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(appointmentsService.getAppointmentById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Appointments> updatePatient(@PathVariable Long id, @RequestBody Appointments appointmentDetails) {
        try {
            Appointments updateAppointmentStatus = appointmentsService.updateAppointment(id, appointmentDetails);
            return ResponseEntity.ok(updateAppointmentStatus);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

