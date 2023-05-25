package com.cluz.doctorlabel.client.feignClient;

import com.cluz.doctorlabel.client.response.Label;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @FeignClient(name = "label-service", url = "http://ec2-18-232-146-72.compute-1.amazonaws.com", path = "/labels")
@FeignClient(name = "label-service", url = "localhost:8090", path = "/labels")

@Component
public interface LabelFeignClient {
    @PostMapping()
    Label createLabel(@RequestBody Label label);

    @GetMapping()
    List<Label> findAll();

    @GetMapping("/{code}")
    Label getLabelByCode(@PathVariable String code);

    @PutMapping("/{code}")
    Label updateLabel(@PathVariable String code, @RequestBody Label label);

    @DeleteMapping("/{code}")
    void deleteLabel(@PathVariable String code);
}