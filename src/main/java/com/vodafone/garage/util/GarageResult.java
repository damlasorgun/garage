package com.vodafone.garage.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GarageResult {
  private Integer vehicleId;
  private Boolean success;
  private String message;
}
