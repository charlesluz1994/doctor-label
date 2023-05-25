package com.cluz.doctorlabel.errors;

public class DoctorUnauthorizedException extends DoctorGenericException {
    public DoctorUnauthorizedException() {
        super("Unauthorized.");
    }

}
