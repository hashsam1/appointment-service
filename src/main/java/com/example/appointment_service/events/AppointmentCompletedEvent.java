// src/main/java/com/example/HMS/patients/events/AppointmentCompletedEvent.java
package com.example.appointment_service.events;

public class AppointmentCompletedEvent {
    private Long appointmentId;
    private Long patientId;
    private double consultationFee;

    public AppointmentCompletedEvent() {}

    public AppointmentCompletedEvent(Long appointmentId, Long patientId, double consultationFee) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.consultationFee = consultationFee;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }
}
