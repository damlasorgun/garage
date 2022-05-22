package com.vodafone.garage.repository;

import com.vodafone.garage.model.Vehicle;
import com.vodafone.garage.util.GarageResult;

public interface IGarageRepository {

  GarageResult allocateVehicleToGarage(Vehicle vehicle);
  GarageResult leaveVehicleFromGarage(int parkId);
  GarageResult getStatusGarage();
}
