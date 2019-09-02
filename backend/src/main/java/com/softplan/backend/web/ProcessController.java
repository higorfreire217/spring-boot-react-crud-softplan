package com.softplan.backend.web;

import com.softplan.backend.model.Process;
import com.softplan.backend.model.ProcessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
class ProcessController {
    private final Logger log = LoggerFactory.getLogger(ProcessController.class);
    private ProcessRepository processRepository;

    public ProcessController(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @GetMapping("/processes")
    Collection<Process> processes() {
        return processRepository.findAll();
    }

    @GetMapping("/process/{id}")
    ResponseEntity<?> getProcess(@PathVariable Long id) {
        Optional<Process> process = processRepository.findById(id);
        return process.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/process")
    ResponseEntity<Process> createProcess(@Valid @RequestBody Process process) throws URISyntaxException {
        log.info("Request to create process: {}", process);
        Process result = processRepository.save(process);
        return ResponseEntity.created(new URI("/api/process/" + result.getId()))
                .body(result);
    }

    @PutMapping("/process/{id}")
    ResponseEntity<Process> updateProcess(@Valid @RequestBody Process process) {
        log.info("Request to update process: {}", process);
        Process result = processRepository.save(process);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/process/{id}")
    public ResponseEntity<?> deleteProcess(@PathVariable Long id) {
        log.info("Request to delete process: {}", id);
        processRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}