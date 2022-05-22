package com.vodafone.garage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
  private String ticketNo;
  private Vehicle vehicle;
}
