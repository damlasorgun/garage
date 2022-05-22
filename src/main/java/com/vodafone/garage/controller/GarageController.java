package com.vodafone.garage.controller;

import com.vodafone.garage.dto.VehicleDto;
import com.vodafone.garage.service.IGarageService;
import com.vodafone.garage.util.GarageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/garage")
public class GarageController {

  private static final Logger logger = LoggerFactory.getLogger(GarageController.class);

  @Autowired
  IGarageService garageService;

  @PostMapping("/park")
  public ResponseEntity<String> allocateVehicleToGarage(@Validated @RequestBody VehicleDto vehicleDto) {
    GarageResult garageResult = garageService.allocateVehicleToGarage(vehicleDto);
    logger.info("Par result = " + garageResult.getMessage()+" "+garageResult.getSuccess());
    return new ResponseEntity<>(garageResult.getMessage(), HttpStatus.OK);
  }

  @DeleteMapping("/leave/{parkId}")
  public ResponseEntity<String> leaveVehicleFromGarage(@PathVariable("parkId") int parkId) {
    GarageResult garageResult = garageService.leaveVehicleFromGarage(parkId);
    logger.info("Leave "+parkId+ " result " + garageResult.getMessage()+" "+garageResult.getSuccess());
    return new ResponseEntity<>(garageResult.getMessage(), HttpStatus.OK);
  }

  @GetMapping("/status")
  public ResponseEntity<String> getStatusGarage() {
    GarageResult garageResult = garageService.getStatusGarage();
    logger.info("Status result = " + garageResult.getMessage()+" "+garageResult.getSuccess());
    return new ResponseEntity<>(garageResult.getMessage(), HttpStatus.OK);
  }

}
