package com.vodafone.garage.repository;

import com.vodafone.garage.enums.GarageStatusEnum;
import com.vodafone.garage.model.Vehicle;
import com.vodafone.garage.model.Ticket;
import com.vodafone.garage.util.GarageResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class GarageRepositoryImpl implements IGarageRepository {
  private static Map<Integer, Integer> garageSlotMap = new TreeMap<>();
  private static List<Vehicle> vehicleList = new ArrayList<>();
  private static List<Ticket> ticketList = new ArrayList<>();

  public GarageRepositoryImpl() {
    for (int i = 1; i < 11; i++) {
      garageSlotMap.put(i, GarageStatusEnum.SLOT_FREE.getSlotValue());
    }
  }

  @Override
  public GarageResult allocateVehicleToGarage(Vehicle vehicle) {
    GarageResult garageResult = new GarageResult();
    for (Map.Entry<Integer, Integer> entry : garageSlotMap.entrySet()) {
      if (entry.getValue() == GarageStatusEnum.SLOT_FREE.getSlotValue() &&
          garageCheckByVehicle(garageSlotMap, entry.getKey(), vehicle.getSlotsSize())) {

        for (int i = entry.getKey(); i < vehicle.getSlotsSize() + entry.getKey(); i++) {

          if (i == vehicle.getSlotsSize() + entry.getKey() - 1 && i != garageSlotMap.size()) {
            garageSlotMap.replace(i + 1, GarageStatusEnum.SLOT_SPACE.getSlotValue());
          }
          garageSlotMap.replace(i, GarageStatusEnum.SLOT_FULL.getSlotValue());
          vehicle.getSlots().add(i);
        }

        vehicleList.add(vehicle);
        ticketList.add(new Ticket(vehicle.getPlate(), vehicle));
        garageResult = GarageResult.builder()
            .message(new StringBuilder()
                .append("Allocated ")
                .append(vehicle.getSlotsSize())
                .append(" slot")
                .append(vehicle.getSlotsSize() > 1 ? "s" : "")
                .toString())
            .success(true)
            .vehicleId(vehicleList.size())
            .build();
        break;
      } else {
        garageResult = GarageResult.builder()
            .message("Garage is full")
            .success(false)
            .build();

      }
    }
    return garageResult;
  }

  @Override
  public GarageResult leaveVehicleFromGarage(int parkId) {
    if (parkId <= vehicleList.size()) {
      Vehicle vehicle = vehicleList.get(parkId - 1);

      for (int i = 0; i < vehicle.getSlotsSize(); i++) {
        garageSlotMap.replace(vehicle.getSlots().get(i), GarageStatusEnum.SLOT_FREE.getSlotValue());
        if (i == vehicle.getSlotsSize() - 1 && vehicle.getSlots().get(i) != garageSlotMap.size()) {
          garageSlotMap.replace(vehicle.getSlots().get(i) + 1, GarageStatusEnum.SLOT_FREE.getSlotValue());
        }
      }

      vehicleList.remove(parkId - 1);
      ticketList.remove(parkId - 1);

      return GarageResult.builder()
          .vehicleId(parkId)
          .message("Vehicle left")
          .success(true)
          .build();
    }

    return GarageResult.builder()
        .message("Can not find")
        .success(false)
        .build();
  }

  @Override
  public GarageResult getStatusGarage() {
    if (vehicleList.isEmpty()) {
      return GarageResult.builder().message("Garage is empty.").success(true).build();
    }
    StringBuilder stringBuilder = new StringBuilder();
    for (Vehicle vehicle : vehicleList)
      stringBuilder
          .append(vehicle.getPlate()).append(" ")
          .append(vehicle.getColour()).append(" [")
          .append(vehicle.getSlots().stream().map(Object::toString)
              .collect(Collectors.joining(", ")))
          .append("]")
          .append("\n");
    return GarageResult.builder().message(stringBuilder.toString()).success(true).build();
  }

  private boolean garageCheckByVehicle(Map<Integer, Integer> garageSlotMap,
                                       Integer parkId, int slot) {
    if (slot + parkId - 1 > garageSlotMap.size()) {
      return false;
    }

    for (int i = parkId; i < parkId + slot; i++) {
      if (garageSlotMap.get(i) != GarageStatusEnum.SLOT_FREE.getSlotValue())
        return false;
      if ((i != 1 && garageSlotMap.get(i - 1) == 1)
          || (i != garageSlotMap.size() && garageSlotMap.get(i + 1) == 1)) {
        return false;
      }

      if (i == garageSlotMap.size()) {
        return true;
      }
    }
    return true;
  }

}
