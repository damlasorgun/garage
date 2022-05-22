package com.vodafone.garage.factory;

import com.vodafone.garage.model.Car;
import com.vodafone.garage.model.Jeep;
import com.vodafone.garage.model.Truck;
import com.vodafone.garage.model.Vehicle;
import com.vodafone.garage.dto.VehicleDto;
import java.util.Locale;

public class VehicleFoctory {
  public static Vehicle createVehicle(VehicleDto vehicleDto) {
    if (vehicleDto.getVehicleType().toUpperCase(Locale.ROOT).equals("CAR")){
      return new Car(vehicleDto.getColour(), vehicleDto.getPlate());
    } else if (vehicleDto.getVehicleType().toUpperCase(Locale.ROOT).equals("JEEP")){
      return new Jeep(vehicleDto.getColour(), vehicleDto.getPlate());
    } else if (vehicleDto.getVehicleType().toUpperCase(Locale.ROOT).equals("TRUCK")){
      return new Truck(vehicleDto.getColour(), vehicleDto.getPlate());
    } else {
      return null;
    }
  }
}
