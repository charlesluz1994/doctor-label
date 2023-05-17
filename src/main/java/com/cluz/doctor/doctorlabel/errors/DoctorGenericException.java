package com.cluz.doctor.doctorlabel.errors;

public class DoctorGenericException extends RuntimeException {
    public DoctorGenericException() {
        super("Generic error.");
    }

    public DoctorGenericException(String message) {
        super(message);
    }
}
