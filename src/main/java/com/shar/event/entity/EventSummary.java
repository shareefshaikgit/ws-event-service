package com.shar.event.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document("EventSummary")
public class EventSummary implements Serializable {

  private static final long serialVersionUID = 4757893637130089245L;

  @Id private String eventSummaryId;

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
