package com.shar.event.mapper;

import com.shar.event.dto.EventSummaryDTO;
import com.shar.event.entity.EventSummary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventSummaryMapper{

  EventSummaryDTO mapToEventSummaryDTO(EventSummary eventSummary);
}
