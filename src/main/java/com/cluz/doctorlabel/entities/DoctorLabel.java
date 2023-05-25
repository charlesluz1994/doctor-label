package com.cluz.doctorlabel.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cases")
public class DoctorLabel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "case_id")
    Long caseId;

    @Column(name = "case_description")
    String caseDescription;

    @Column(name = "doctor_id")
    Integer doctorId;

    @Column(name = "label")
    String label;

    @Column(name = "time_to_label")
    LocalDateTime timeToLabel;
}