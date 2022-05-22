package com.vodafone.garage.service;

import com.vodafone.garage.util.GarageResult;
import com.vodafone.garage.dto.VehicleDto;

public interface IGarageService {
  GarageResult allocateVehicleToGarage(VehicleDto vehicleDto);
  GarageResult leaveVehicleFromGarage(int parkId);
  GarageResult getStatusGarage();
}
