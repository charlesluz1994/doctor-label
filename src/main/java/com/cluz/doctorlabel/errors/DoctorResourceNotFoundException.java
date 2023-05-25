package com.cluz.doctorlabel.errors;

import java.util.UUID;

public class DoctorResourceNotFoundException extends DoctorGenericException {
    public DoctorResourceNotFoundException() {
        super("Resource not found.");
    }

    public DoctorResourceNotFoundException(UUID id) {
        super(String.format("Resource not found with id: %s.", id));
    }

    public DoctorResourceNotFoundException(String resource) {
        super(String.format("%s not found.", resource));
    }

    public DoctorResourceNotFoundException(String resource, UUID id) {
        super(String.format("%s not found with id: %s.", resource, id));
    }
}
