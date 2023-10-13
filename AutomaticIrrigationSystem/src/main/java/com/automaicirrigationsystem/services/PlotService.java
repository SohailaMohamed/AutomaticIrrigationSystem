package com.automaicirrigationsystem.services;

import com.automaicirrigationsystem.model.Plot;
import com.automaicirrigationsystem.util.APIResponse;

import java.util.List;
import java.util.Optional;

public interface PlotService {

    Plot createPlot(Plot plot);

    Optional<Plot> getPlotById(Long plotId);

    List<Plot> getAllPlots();

    Plot updatePlot(Long plotId, Plot plot);

    void deletePlotById(Long id);

    String deletePlotByIPlotId(Long plotId);
}