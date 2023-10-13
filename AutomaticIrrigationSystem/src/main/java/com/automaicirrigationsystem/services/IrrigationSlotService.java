package com.automaicirrigationsystem.services;

import com.automaicirrigationsystem.model.IrrigationSlot;

import java.util.List;
import java.util.Optional;

public interface IrrigationSlotService {

    IrrigationSlot addIrrigationSlot(IrrigationSlot slot);

    Optional<IrrigationSlot> getIrrigationSlotByIrrigationSlotId(Long irrigationSlotId);
    IrrigationSlot addIrrigationSlot(Long plotId, IrrigationSlot slot);

    IrrigationSlot findIrrigationSlotByPlotIdAndIrrigationSlotId(Long plotId, Long irrigationSlotId);
    IrrigationSlot updateIrrigationSlotByPlotIdAndIrrigationSlotId(Long plotId, Long irrigationSlotId, IrrigationSlot irrigationSlot);
    List<IrrigationSlot> getIrrigationSlotsByPlotId(Long plotId);
    String updateIrrigationStatus(Long irrigationSlotId, String status);

    String getIrrigationStatus(Long irrigationSlotId);

    String deleteIrrigationSlotByPlotIdAndIrrigationSlotId(Long plotId, Long irrigationSlotId);
}