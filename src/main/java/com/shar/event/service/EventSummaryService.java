package com.shar.event.service;

import com.shar.event.dto.EventSummaryDTO;
import com.shar.event.entity.EventSummary;
import com.shar.event.mapper.EventSummaryMapper;
import com.shar.event.repository.EventSummaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class EventSummaryService {

  private final EventSummaryRepository eventSummaryRepository;
  private final EventSummaryMapper eventSummaryMapper;

  public Flux<EventSummaryDTO> findByEventId(String eventId) {
    Flux<EventSummary> eventSummaryFlux = eventSummaryRepository.findByEventId(eventId);
    Flux<EventSummaryDTO> eventSummaryDTOFlux =
        eventSummaryFlux.map(eventSummaryMapper::mapToEventSummaryDTO);
    return eventSummaryDTOFlux;
  }

  public Flux<EventSummaryDTO> getAllEventsSummary() {
    Flux<EventSummary> eventSummaryFlux = eventSummaryRepository.findAll();
    Flux<EventSummaryDTO> eventSummaryDTOFlux =
        eventSummaryFlux.map(eventSummaryMapper::mapToEventSummaryDTO);
    return eventSummaryDTOFlux;
  }

}
