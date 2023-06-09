package com.cluz.doctorlabel.repositories;

import com.cluz.doctorlabel.entities.DoctorLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorLabelRepository extends JpaRepository<DoctorLabel, Long> {

    DoctorLabel save(DoctorLabel doctorLabel);

    List<DoctorLabel> findAll();

    Optional<DoctorLabel> findById(Long id);

    Optional<List<DoctorLabel>> findByLabel(String label);


    @Modifying
    @Transactional
    @Query("update DoctorLabel d set d.label = null where d.label = :label and d.caseId = :caseId")
    void deleteLabelFromCases(Long caseId, String label);

    void deleteById(Long id);

}
