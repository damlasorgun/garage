package com.vodafone.garage.model;

import com.vodafone.garage.util.Const;

public class Jeep extends Vehicle{
  public Jeep(String colour, String plate) {
    super(Const.VEHICLE_JEEP, colour,plate, Const.NUMBER_OF_JEEP_SLOTS);
  }
}
