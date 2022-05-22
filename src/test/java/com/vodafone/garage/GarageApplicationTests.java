package com.vodafone.garage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.vodafone.garage.dto.VehicleDto;
import com.vodafone.garage.repository.GarageRepositoryImpl;
import com.vodafone.garage.service.GarageServiceImpl;
import com.vodafone.garage.service.IGarageService;
import com.vodafone.garage.util.GarageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarageApplicationTests {

  @InjectMocks
  private GarageRepositoryImpl garageRepository;

  @Mock
  private IGarageService garageService;

  @BeforeEach
  public void setUp() {
    garageService = new GarageServiceImpl(garageRepository);
  }



  @Test
  public void allocateVehicleToGarage() {
    GarageResult garageResult = GarageResult.builder().message("Allocated 1 slot").success(true).vehicleId(1).build();
    GarageResult garageResult1 = garageService.allocateVehicleToGarage(new VehicleDto("Car","06-GKR-30","Gri"));

    assertEquals(garageResult.getVehicleId(),garageResult1.getVehicleId());
    assertEquals(garageResult.getMessage(),garageResult1.getMessage());
    assertEquals(garageResult.getSuccess(),garageResult1.getSuccess());


  }

  @Test
  void leaveVehicleFromGarage() {
    int parkId=1;
    garageService.allocateVehicleToGarage(new VehicleDto("Car","06-GKR-30","Gri"));
    GarageResult garageResult = GarageResult.builder().message("Vehicle left").success(true).vehicleId(1).build();
    GarageResult garageResult1 = garageService.leaveVehicleFromGarage(parkId);

    assertEquals(garageResult.getVehicleId(),garageResult1.getVehicleId());
    assertEquals(garageResult.getMessage(),garageResult1.getMessage());
    assertEquals(garageResult.getSuccess(),garageResult1.getSuccess());
  }

  @Test
  void getStatusGarage() {
    garageService.allocateVehicleToGarage(new VehicleDto("Car","06-GKR-30","Gri"));
    GarageResult garageResult = GarageResult.builder().message("06-GKR-30 Gri [1]\n").success(true).vehicleId(1).build();
    GarageResult garageResult1 = garageService.getStatusGarage();

    assertEquals(garageResult.getMessage(),garageResult1.getMessage());
    assertEquals(garageResult.getSuccess(),garageResult1.getSuccess());
  }
}
