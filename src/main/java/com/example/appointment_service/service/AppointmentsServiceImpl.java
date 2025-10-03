
package com.example.appointment_service.service;
import com.example.appointment_service.events.AppointmentCompletedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import com.example.appointment_service.model.Appointments;

import com.example.appointment_service.repository.AppointmentsRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentsServiceImpl implements com.example.appointment_service.service.AppointmentsService {

    private final AppointmentsRepository appointmentsRepository;

    private final KafkaTemplate<String, AppointmentCompletedEvent> kafkaTemplate;

    public AppointmentsServiceImpl(AppointmentsRepository appointmentsRepository, KafkaTemplate<String, AppointmentCompletedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.appointmentsRepository = appointmentsRepository;

    }

    //post
    @Override
    public Appointments createAppointment(Appointments appointment) {
        if (appointment.getPatient() == null ) {
            throw new IllegalArgumentException("Patient ID is required");
        }

        return appointmentsRepository.save(appointment);
    }

    //get all
    @Override
    public List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }

    //get
    @Override
    public Appointments getAppointmentById(Long id) {
        return appointmentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    }

    //put
    @Override
    public Appointments updateAppointment(Long id, Appointments updatedDetails) {
        Appointments existing = appointmentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        existing.setDoctor_name(updatedDetails.getDoctor_name());
        existing.setTime_of_appointment(updatedDetails.getTime_of_appointment());
        existing.setAppointment_amt(updatedDetails.getAppointment_amt());
        existing.setAppointment_type(updatedDetails.getAppointment_type());
//        existing.setStatus(updatedDetails.getStatus());

        // If patientId  needs to be updated
        if (updatedDetails.getPatient() != null) {
            existing.setPatient(updatedDetails.getPatient());
        }

        //edge case for
        //1. no appointment by the appointment_id is there
        //2. check if the patientId should already exist
        return appointmentsRepository.save(existing);
    }


    //kafka event
    @Override
    public Appointments completeAppointment(Long id) {
        // 1. Find appointment
        Appointments appointment = appointmentsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        // 2. If already completed, just return
        if (Appointments.AppointmentStatus.COMPLETED.equals(appointment.getStatus())) {
            return appointment;
        }

        appointment.setStatus(Appointments.AppointmentStatus.COMPLETED);
        Appointments saved = appointmentsRepository.save(appointment);

        // 4. Build event
        AppointmentCompletedEvent event = new AppointmentCompletedEvent(
                saved.getAppointment_id(),
                saved.getPatient() != null ? saved.getPatient() : null,
                saved.getAppointment_amt()
        );

        //PRODUCER

        // 5. Send event to Kafka
        kafkaTemplate.send("appointments.completed", event);

        return saved;
    }

}
