package com.example.appointment_service.model;

import java.time.LocalTime;


import jakarta.persistence.*;

@Entity
@Table(name = "appointments")

public class Appointments {

    public enum AppointmentStatus {
        SCHEDULED,
        COMPLETED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointment_id;

    private String appointment_type;
    private double appointment_amt;
    private Long patient_id;


//    private Long bill_id;

    private String doctor_name;
    private LocalTime time_of_appointment;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    public Appointments() {}

    public String getAppointment_type() {
        return appointment_type;
    }

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public Appointments(Long appointment_id, String doctor_name, Long patient_id, LocalTime time_of_appointment, AppointmentStatus status, String appointment_type,double appointment_amt) {
        this.appointment_id = appointment_id;
        this.doctor_name = doctor_name;
        this.patient_id = patient_id;
        this.time_of_appointment = time_of_appointment;
        this.status = status;
        this.appointment_type=appointment_type;
        this.appointment_amt=appointment_amt;
    }

    public Long getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(Long appointment_id) {
        this.appointment_id = appointment_id;
    }

    public Long getPatient() {
        return patient_id;
    }

    public void setPatient(Long patient) {
        this.patient_id = patient;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public LocalTime getTime_of_appointment() {
        return time_of_appointment;
    }

    public void setTime_of_appointment(LocalTime time_of_appointment) {
        this.time_of_appointment = time_of_appointment;
    }

    public double getAppointment_amt() {
        return appointment_amt;
    }

    public void setAppointment_amt(double appointment_amt) {
        this.appointment_amt = appointment_amt;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }


}
