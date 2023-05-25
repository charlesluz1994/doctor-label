package com.cluz.doctorlabel.controllers;

import com.cluz.doctorlabel.entities.DoctorLabel;
import com.cluz.doctorlabel.repositories.DoctorLabelRepository;
import com.cluz.doctorlabel.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor-labels")
public class DoctorController {
    private final DoctorLabelRepository doctorLabelRepository;

    private final DoctorService doctorService;

    @PostMapping()
    public ResponseEntity<DoctorLabel> createCase(@RequestBody DoctorLabel doctorLabel) {
        // doctorLabel.setLabel(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.create(doctorLabel));
    }

    @GetMapping
    public ResponseEntity<List<DoctorLabel>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.findAll());
    }

    @GetMapping("/{id}")
    // @Cacheable(value = "doctorLabelByIdCache", key = "#id")
    public ResponseEntity<DoctorLabel> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.findById(id));
    }

    @GetMapping("/label-code/{label}")
    // @Cacheable(value = "doctorLabelByLabelIdCache", key = "#by-label")
    public ResponseEntity<List<DoctorLabel>> findByLabel(@PathVariable String label) {
        List<DoctorLabel> existingCasesByLabel = doctorService.findByLabel(label);
        return ResponseEntity.status(HttpStatus.OK).body(existingCasesByLabel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorLabel> update(@PathVariable Long id, @RequestBody DoctorLabel doctorLabel) {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.update(id, doctorLabel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/label/{caseId}/{label}")
    public ResponseEntity<Void> deleteLabelFromCases(@PathVariable Long caseId,@PathVariable String label) {
        doctorLabelRepository.deleteLabelFromCases(caseId, label);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}