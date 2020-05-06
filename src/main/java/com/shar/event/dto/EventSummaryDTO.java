package com.shar.event.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EventSummaryDTO implements Serializable {

  private static final long serialVersionUID = -731565535158709066L;

  private String eventSummaryId;

  private String eventId;

  private String eventMonth;

  private String baseLocation;

  private String beneficiaryName;

  private String venueAddress;

  private String councilName;

  private String project;

  private String category;

  private String eventName;

  private String eventDescription;

  private String eventDate;

  private String totalVolunteersCount;

  private String totalVolunteerHours;

  private String totalTravelHours;

  private String overallVolunteerHours;

  private String livesImpacted;

  private String activityType;

  private String status;

  private String eventPocId;

  private String eventPocName;

  private String pocContactNumber;
}
