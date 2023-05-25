package com.cluz.doctorlabel.services;

import com.cluz.doctorlabel.client.feignClient.LabelFeignClient;
import com.cluz.doctorlabel.client.response.Label;
import com.cluz.doctorlabel.entities.DoctorLabel;
import com.cluz.doctorlabel.errors.DoctorResourceNotFoundException;
import com.cluz.doctorlabel.repositories.DoctorLabelRepository;
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

        String label = doctorLabel.getLabel();
        Label receivedLabel = labelFeignClient.getLabelByCode(label);
        if (receivedLabel.getCode() == null) {
            throw new DoctorResourceNotFoundException("Label with code " + doctorLabel.getLabel());
        }
        //doctorLabel.setLabel(label.getLabelCode());
        return doctorLabelRepository.save(doctorLabel);
    }

    public DoctorLabel update(Long id, DoctorLabel doctorLabel) {
        DoctorLabel existingDoctorLabel = doctorLabelRepository.findById(id).orElseThrow(() -> new DoctorResourceNotFoundException("Doctor Label not found with id " + id));

        Label label = labelFeignClient.getLabelByCode(doctorLabel.getLabel());
        if (label.getCode() == null) {
            throw new DoctorResourceNotFoundException("Label not found " + doctorLabel.getLabel());
        }
        existingDoctorLabel.setCaseId(doctorLabel.getCaseId());
        existingDoctorLabel.setCaseDescription(doctorLabel.getCaseDescription());
        existingDoctorLabel.setDoctorId(doctorLabel.getDoctorId());
        existingDoctorLabel.setLabel(label.getCode());
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