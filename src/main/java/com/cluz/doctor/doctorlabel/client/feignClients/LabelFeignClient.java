package com.cluz.doctor.doctorlabel.client.feignClients;

import com.cluz.doctor.doctorlabel.client.response.Label;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "label-service", url = "http://localhost:80", path = "/labels")
@Component
public interface LabelFeignClient {
    @PostMapping()
    Label createLabel(@RequestBody Label label);

    @GetMapping()
    List<Label> findAll();

    @GetMapping("/code/{code}")
    Label getLabelByCode(@PathVariable String code);

    @PutMapping("/{code}")
    Label updateLabel(@PathVariable String code, @RequestBody Label label);

    @DeleteMapping("/{code}")
    void deleteLabel(@PathVariable String code);
}