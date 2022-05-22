package com.vodafone.garage.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.vodafone.garage.dto.VehicleDto;
import com.vodafone.garage.service.IGarageService;
import com.vodafone.garage.util.GarageResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(value = GarageController.class)
class GarageControllerTest {

  @MockBean
  private IGarageService garageService;

  @Autowired
  private MockMvc mvc;


  @Test
  public void allocateVehicleToGarage() throws Exception {
    GarageResult garageResult = GarageResult.builder().message("Allocated 1 slot").success(true).vehicleId(1).build();

    Mockito.when(garageService.allocateVehicleToGarage(new VehicleDto("Car","06-GKR-30","Gri"))).thenReturn(garageResult);

    mvc.perform(MockMvcRequestBuilders
            .post("/garage/park")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content("{ \"vehicleType\": \"Car\",\"plate\": \"06-GKR-30\", \"colour\": \"Gri\" }"))
        .andExpect(status().isOk());
  }

  @Test
  void leaveVehicleFromGarage() throws Exception {
    int parkId=1;
    garageService.allocateVehicleToGarage(new VehicleDto("Car","06-GKR-30","Gri"));
    GarageResult garageResult = GarageResult.builder().message("Vehicle left").success(true).vehicleId(1).build();

    Mockito.when(garageService.leaveVehicleFromGarage(1))
        .thenReturn(garageResult);

    mvc.perform(MockMvcRequestBuilders
            .delete("/garage/leave/1"))
        .andExpect(status().isOk());

  }

  @Test
  void getStatusGarage() throws Exception {
    garageService.allocateVehicleToGarage(new VehicleDto("Car","06-GKR-30","Gri"));
    GarageResult garageResult = GarageResult.builder().message("06-GKR-30 Gri [1]\n").success(true).vehicleId(1).build();

    Mockito.when(garageService.getStatusGarage())
        .thenReturn(garageResult);

    mvc.perform(MockMvcRequestBuilders
            .get("/garage/status")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }
}