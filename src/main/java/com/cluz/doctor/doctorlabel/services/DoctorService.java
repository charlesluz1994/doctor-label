package com.cluz.doctor.doctorlabel.services;

import com.cluz.doctor.doctorlabel.client.feignClients.LabelFeignClient;
import com.cluz.doctor.doctorlabel.client.response.Label;
import com.cluz.doctor.doctorlabel.entities.DoctorLabel;
import com.cluz.doctor.doctorlabel.errors.DoctorResourceNotFoundException;
import com.cluz.doctor.doctorlabel.repositories.DoctorLabelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorLabelRepository doctorLabelRepository;

    private final LabelFeignClient labelFeignClient;

    public DoctorLabel create(DoctorLabel doctorLabel) {

        Label label = labelFeignClient.getLabelByCode(doctorLabel.getLabel());
        if (label == null) {
            //Here I can change to create a new label in the future. Instead of throw this exception.
            throw new DoctorResourceNotFoundException("Label not found with code " + doctorLabel.getLabel());
        }
        doctorLabel.setLabel(label.getLabelCode());
        return doctorLabelRepository.save(doctorLabel);
    }

    public DoctorLabel update(Long id, DoctorLabel doctorLabel) {
        DoctorLabel existingDoctorLabel = doctorLabelRepository.findById(id).get();
        if (existingDoctorLabel == null) {
            throw new DoctorResourceNotFoundException("Doctor Label not found with id " + id);
        }
        Label label = labelFeignClient.getLabelByCode(doctorLabel.getLabel());
        if (label == null) {
            throw new DoctorResourceNotFoundException("Doctor Label not found with Label " + doctorLabel.getLabel());
        }
        existingDoctorLabel.setCaseId(doctorLabel.getCaseId());
        existingDoctorLabel.setCaseDescription(doctorLabel.getCaseDescription());
        existingDoctorLabel.setDoctorId(doctorLabel.getDoctorId());
        existingDoctorLabel.setLabel(label.getLabelCode());
        existingDoctorLabel.setTimeToLabel(LocalDateTime.now());
        return doctorLabelRepository.save(existingDoctorLabel);
    }

    public List<DoctorLabel> findAll() {
        return doctorLabelRepository.findAll();
    }

    public DoctorLabel findById(Long id) {
        DoctorLabel existingDoctorLabel = doctorLabelRepository.findById(id).get();
        if (existingDoctorLabel == null) {
            throw new DoctorResourceNotFoundException("Doctor Label not found with id " + existingDoctorLabel.getCaseId());
        }
        return existingDoctorLabel;
    }

    public List<DoctorLabel> findByLabel(String labelCode) {
        Optional<List<DoctorLabel>> doctorLabels = doctorLabelRepository.findByLabel(labelCode);
        //Label label = labelFeignClient.findByCode(labelCode);
        if (doctorLabels.isEmpty()) {
            throw new DoctorResourceNotFoundException("Label not found with code " + labelCode);
        }
        return doctorLabels.get();
    }

    public void delete(Long id) {
        DoctorLabel existingDoctorLabel = doctorLabelRepository.findById(id).get();
        if (existingDoctorLabel == null) {
            throw new DoctorResourceNotFoundException("Doctor Label not found with id " + id);
        }
        doctorLabelRepository.deleteById(id);
    }
}
/**
 * Com essa configuração, podemos recuperar todos os casos associados a uma etiqueta usando o método findByLabelId() do repositório DoctorLabelRepository:
 * List<DoctorLabel> doctorLabels = doctorLabelRepository.findByLabelId(labelId);
 * <p>
 * Além disso, podemos obter a etiqueta associada a um caso específico usando o método getLabel() da classe DoctorLabel:
 * <p>
 * DoctorLabel doctorLabel = doctorLabelRepository.findById(doctorLabelId);
 * Label label = doctorLabel.getLabel();
 */