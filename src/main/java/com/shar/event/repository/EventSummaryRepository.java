package com.shar.event.repository;

import com.shar.event.entity.EventSummary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EventSummaryRepository extends ReactiveMongoRepository<EventSummary, String> {

  Flux<EventSummary> findByEventId(String eventId);
}
