package com.vodafone.garage.model;

import com.vodafone.garage.util.Const;

public class Truck extends Vehicle{
  public Truck(String colour, String plate) {
    super(Const.VEHICLE_TRUCK, colour,plate, Const.NUMBER_OF_TRUCK_SLOTS);
  }
}
