package com.vodafone.garage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
@AllArgsConstructor
public class VehicleDto {

  @NotNull
  @NotBlank
  private String vehicleType;

  @NotNull
  @NotBlank
  private String plate;

  @NotNull
  @NotBlank
  private String colour;

}
