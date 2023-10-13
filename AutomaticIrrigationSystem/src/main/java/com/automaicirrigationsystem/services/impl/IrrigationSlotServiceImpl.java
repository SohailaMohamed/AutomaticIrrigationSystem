package com.automaicirrigationsystem.services.impl;

import com.automaicirrigationsystem.model.Crop;
import com.automaicirrigationsystem.model.IrrigationSlot;

import com.automaicirrigationsystem.repositories.IrrigationSlotRepository;
import com.automaicirrigationsystem.services.IrrigationSlotService;
import com.automaicirrigationsystem.services.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IrrigationSlotServiceImpl implements IrrigationSlotService {
    @Autowired
    private PlotService plotService;
    @Autowired
    private IrrigationSlotRepository irrigationSlotRepository;

    @Override
    public IrrigationSlot addIrrigationSlot(IrrigationSlot slot) {
        return irrigationSlotRepository.save(slot);
    }

    @Override
    public Optional<IrrigationSlot> getIrrigationSlotByIrrigationSlotId(Long irrigationSlotId) {
        Optional<IrrigationSlot> irrigationSlot = irrigationSlotRepository.findById(irrigationSlotId);
        if (irrigationSlot.isPresent()) {
            return irrigationSlot;
        }
        throw new RuntimeException("Irrigation Schedule not found");
    }
    @Override
    public IrrigationSlot addIrrigationSlot(Long plotId, IrrigationSlot slot) {
        plotService.getPlotById(plotId);
        return irrigationSlotRepository.save(slot);
    }

    @Override
    public IrrigationSlot findIrrigationSlotByPlotIdAndIrrigationSlotId(Long plotId, Long irrigationSlotId) {
        plotService.getPlotById(plotId);
        Optional<IrrigationSlot> irrigationSlot = Optional.of(irrigationSlotRepository.findByIrrigationSlotIdAndPlotId(irrigationSlotId, plotId).get());
        if (irrigationSlot.isPresent()) {
            return irrigationSlot.get();
        }
        throw new RuntimeException("Irrigation Slot not found");
    }

    @Override
    public IrrigationSlot updateIrrigationSlotByPlotIdAndIrrigationSlotId(Long irrigationSlotId, Long plotId, IrrigationSlot irrigationSlot) {
        plotService.getPlotById(plotId);
        findIrrigationSlotByPlotIdAndIrrigationSlotId(irrigationSlotId, plotId);
        if(irrigationSlotId.equals(irrigationSlot.getIrrigationSlotId()) && plotId.equals(irrigationSlot.getPlot().getId())){
            return irrigationSlotRepository.saveAndFlush(irrigationSlot);
        }
        throw new RuntimeException("Irrigation Slot ID doesn't match");
    }

    @Override
    public List<IrrigationSlot> getIrrigationSlotsByPlotId(Long plotId) {
        plotService.getPlotById(plotId);
        return irrigationSlotRepository.findAllByPlotId(plotId);
    }

    @Override
    public String updateIrrigationStatus(Long irrigationSlotId, String status) {
        Optional<IrrigationSlot> slot = getIrrigationSlotByIrrigationSlotId(irrigationSlotId);
        slot.get().setStatus(status);
        irrigationSlotRepository.saveAndFlush(slot.get());
        return "Irrigation Status Updated to " + status;
    }

    @Override
    public String getIrrigationStatus(Long irrigationSlotId) {
        Optional<IrrigationSlot> slot = getIrrigationSlotByIrrigationSlotId(irrigationSlotId);
        return slot.get().getStatus();
    }

    @Override
    public String deleteIrrigationSlotByPlotIdAndIrrigationSlotId(Long plotId, Long irrigationSlotId) {
        IrrigationSlot irrigationSlot = getIrrigationSlotByIrrigationSlotId(irrigationSlotId).get();
        if(irrigationSlot.getPlot().getId().equals(plotId)){
            irrigationSlotRepository.delete(irrigationSlot);
            return "Irrigation Slot Deleted";
        }
        throw new RuntimeException("Irrigation Slot ID doesn't match");
    }
//    public List<IrrigationSlot> predictIrrigationSchedule(String cropType, Double cultivatedArea) {
//        Crop crop = Crop.valueOf(cropType);
//        Double waterRequirementPerSquareMeter = Math.pow(crop.getValue(),2.0);
//        Double totalWaterRequirement = cultivatedArea * waterRequirementPerSquareMeter;
//
//        List<IrrigationSlot> predictedIrrigationSchedule ;
//        return predictedIrrigationSchedule;
//    }
}