package com.vodafone.garage.model;

import com.vodafone.garage.util.Const;

public class Car extends Vehicle{

  public Car(String colour, String plate) {
    super(Const.VEHICLE_CAR,colour,plate,Const.NUMBER_OF_CAR_SLOTS);
  }
}
