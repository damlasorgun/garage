package com.vodafone.garage.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public abstract class Vehicle {
  private String vehicleType;
  private String plate;
  private String colour;
  private int slotsSize;
  private List<Integer> slots;

  public Vehicle(String vehicleType, String colour, String plate, int slotsSize) {
    this.vehicleType = vehicleType;
    this.colour = colour;
    this.plate = plate;
    this.slotsSize = slotsSize;
    slots = new ArrayList<>();
  }
}
