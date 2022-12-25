package com.softinov.templateapp.quickpoll.controllers;


import com.softinov.templateapp.quickpoll.entities.Vote;
import com.softinov.templateapp.quickpoll.repositories.VoteRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/polls/{pollId}")
public class VoteController {
    private final VoteRepository voteRepository;

    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @PostMapping("/votes")
    public ResponseEntity<?> create(@RequestBody Vote vote, @PathVariable Long pollId) {
        vote = voteRepository.save(vote);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vote.getId())
                .toUri()
        );
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @GetMapping("/votes")
    public Iterable<Vote> getAllVotes(@PathVariable Long pollId) {
        return voteRepository.findByPoll(pollId);
    }
}
