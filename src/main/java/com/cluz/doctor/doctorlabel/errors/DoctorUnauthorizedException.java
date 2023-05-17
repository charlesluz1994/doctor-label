package com.cluz.doctor.doctorlabel.errors;

public class DoctorUnauthorizedException extends DoctorGenericException {
    public DoctorUnauthorizedException() {
        super("Unauthorized.");
    }

}
