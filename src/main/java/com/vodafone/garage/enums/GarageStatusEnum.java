package com.vodafone.garage.enums;

public enum GarageStatusEnum {
  SLOT_FREE(0), SLOT_FULL(1), SLOT_SPACE(2);

  private final int slotValue;

  GarageStatusEnum(int slotValue) {
    this.slotValue = slotValue;
  }

  public int getSlotValue() {
    return slotValue;
  }
}