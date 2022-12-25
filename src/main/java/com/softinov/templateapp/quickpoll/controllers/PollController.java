package com.softinov.templateapp.quickpoll.controllers;

import com.softinov.templateapp.quickpoll.entities.Poll;
import com.softinov.templateapp.quickpoll.repositories.PollRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/polls")
public class PollController {
    private final PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Poll>> getAll() {
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception {
        Optional<Poll> poll = pollRepository.findById(id);
        if (poll.isEmpty())
            throw new Exception("Poll not found");
        return new ResponseEntity<>(poll.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Poll poll) {
        poll = pollRepository.save(poll);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        headers.setLocation(location);
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Poll poll, @PathVariable Long id) throws Exception {
        if (pollRepository.findById(id).isEmpty())
            throw new Exception("Poll not found");

        pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        if (pollRepository.findById(id).isEmpty())
            throw new Exception("Poll not found");

        pollRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
