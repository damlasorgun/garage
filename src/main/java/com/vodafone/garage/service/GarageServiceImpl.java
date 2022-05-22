package com.vodafone.garage.service;

import com.vodafone.garage.factory.VehicleFactory;
import com.vodafone.garage.model.Vehicle;
import com.vodafone.garage.util.GarageResult;
import com.vodafone.garage.dto.VehicleDto;
import com.vodafone.garage.repository.IGarageRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GarageServiceImpl implements IGarageService {

  private static final Logger logger = LoggerFactory.getLogger(GarageServiceImpl.class);
  @Autowired
  IGarageRepository garageRepository;

  @Override
  public GarageResult allocateVehicleToGarage(VehicleDto vehicleDto) {
    Vehicle vehicle = VehicleFactory.createVehicle(new VehicleDto(vehicleDto.getVehicleType().trim(),
        vehicleDto.getPlate().trim(), vehicleDto.getColour().trim()));
    if (vehicle == null) {
      logger.error("Park error Undefined vehicle type = "+vehicleDto.getVehicleType());
      return GarageResult.builder().message("Undefined vehicle type").success(false).build();
    }
    return garageRepository.allocateVehicleToGarage(vehicle);
  }

  @Override
  public GarageResult leaveVehicleFromGarage(int parkId) {
    return garageRepository.leaveVehicleFromGarage(parkId);
  }

  @Override
  public GarageResult getStatusGarage() {
    return garageRepository.getStatusGarage();
  }
}
