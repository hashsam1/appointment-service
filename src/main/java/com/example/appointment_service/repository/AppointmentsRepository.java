
package com.example.appointment_service.repository;

import com.example.appointment_service.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
}

