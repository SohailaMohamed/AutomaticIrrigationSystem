package com.automaicirrigationsystem.services.impl;

import com.automaicirrigationsystem.model.IrrigationSlot;
import com.automaicirrigationsystem.model.Plot;
import com.automaicirrigationsystem.repositories.PlotRepository;
import com.automaicirrigationsystem.services.IrrigationSlotService;
import com.automaicirrigationsystem.services.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlotServiceImpl implements PlotService {
    @Autowired
    private PlotRepository plotRepository;
    @Autowired
    private IrrigationSlotService irrigationSlotService;

    @Override
    public Plot createPlot(Plot plot) {
        return plotRepository.save(plot);
    }

    @Override
    public Optional<Plot> getPlotById(Long plotId) {
        Optional<Plot> plot = plotRepository.findById(plotId);
        if (plot.isPresent()) {
            return plot;
        }
        throw new RuntimeException("Plot not found");
    }

    @Override
    public List<Plot> getAllPlots() {
        return plotRepository.findAll();
    }

    @Override
    public Plot updatePlot(Long plotId, Plot plot) {
        getPlotById(plotId);
        if(plotId.equals(plot.getId())){
            return plotRepository.saveAndFlush(plot);
        }
        throw new RuntimeException("Plot ID doesn't match");
    }

    @Override
    public void deletePlotById(Long id) {
        plotRepository.deleteById(id);
    }

    @Override
    public String deletePlotByIPlotId(Long plotId) {
        getPlotById(plotId);
        List<IrrigationSlot> irrigationSlots = irrigationSlotService.getIrrigationSlotsByPlotId(plotId);
        if(irrigationSlots.isEmpty()){
            for (IrrigationSlot slot : irrigationSlots) {
                irrigationSlotService.deleteIrrigationSlotByPlotIdAndIrrigationSlotId(slot.getIrrigationSlotId(), plotId);
            }
            plotRepository.deleteById(plotId);
            return ("Plot deleted successfully");
        }
        throw new RuntimeException("Plot has irrigation slots");
    }

}