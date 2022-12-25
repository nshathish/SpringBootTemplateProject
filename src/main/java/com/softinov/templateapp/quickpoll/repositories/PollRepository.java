package com.softinov.templateapp.quickpoll.repositories;

import com.softinov.templateapp.quickpoll.entities.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepository extends CrudRepository<Poll, Long> {
}
