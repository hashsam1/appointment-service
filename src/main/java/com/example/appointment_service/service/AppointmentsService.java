

package com.example.appointment_service.service;

import com.example.appointment_service.model.Appointments;

import java.util.List;

public interface AppointmentsService {
    Appointments createAppointment(Appointments appointment);

    List<Appointments> getAllAppointments();

    Appointments getAppointmentById(Long id);

    Appointments updateAppointment(Long id, Appointments updatedDetails);

    Appointments completeAppointment(Long id);

}

