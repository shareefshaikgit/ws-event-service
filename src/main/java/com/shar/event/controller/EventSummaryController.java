package com.shar.event.controller;

import com.shar.event.dto.EventSummaryDTO;
import com.shar.event.service.EventSummaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
@RequestMapping("/event/summary")
public class EventSummaryController {

  private EventSummaryService eventSummaryService;

  @GetMapping(value = "/{eventId}"/*, produces = MediaType.APPLICATION_STREAM_JSON_VALUE*/)
  public Flux<EventSummaryDTO> getEventSummary(
      @PathVariable(value = "eventId", required = true) String eventId) {
    return eventSummaryService.findByEventId(eventId);
  }

  @GetMapping(/*produces = MediaType.APPLICATION_STREAM_JSON_VALUE*/)
  public Flux<EventSummaryDTO> getEventsSummary() {
    return eventSummaryService.getAllEventsSummary();
  }
}
