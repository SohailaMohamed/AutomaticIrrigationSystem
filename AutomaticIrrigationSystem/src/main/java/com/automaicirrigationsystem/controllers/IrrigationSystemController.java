package com.automaicirrigationsystem.controllers;

import com.automaicirrigationsystem.model.IrrigationSlot;
import com.automaicirrigationsystem.model.Plot;
import com.automaicirrigationsystem.services.IrrigationSlotService;
import com.automaicirrigationsystem.services.PlotService;
import com.automaicirrigationsystem.util.APIResponse;
import com.automaicirrigationsystem.util.APIResponseBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IrrigationSystemController {
    private PlotService plotService;
    private IrrigationSlotService irrigationSlotService;
    @Autowired
    public IrrigationSystemController(PlotService plotService, IrrigationSlotService irrigationSlotService) {
        this.plotService = plotService;
        this.irrigationSlotService = irrigationSlotService;
    }


    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping(value = "/plots", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Create a new plot of land.")
    public APIResponse<Plot> addPlot(@RequestBody Plot plot) {
        return new APIResponseBuilder<Plot>().code(200).addMessage("OK").body(plotService.createPlot(plot)).build();

    }

    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "/plots/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Get the details of a plot of land.")
    public APIResponse<Plot> getPlot(@PathVariable long plotId) {
        return new APIResponseBuilder<Plot>().code(200).addMessage("OK")
                .body(plotService.getPlotById(plotId).get()).build();

    }

    @ApiResponse(responseCode = "200", description = "Success")
    @PutMapping(value = "/plots/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Update the details of a plot of land.")
    public APIResponse<Plot> updatePlot(@PathVariable long plotId, @RequestBody Plot plot) {
        return new APIResponseBuilder<Plot>().code(200).addMessage("OK")
                .body(plotService.updatePlot(plotId, plot)).build();
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "/plots", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Get a list of all plots of land.")
    public List<Plot> getPlots() {
        return plotService.getAllPlots();
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping(value = "/plots/{plotId}/irrigation-slots", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Create a new irrigation slot for a plot of land.")
    public IrrigationSlot createIrrigationSlot(@PathVariable Long plotId, @RequestBody IrrigationSlot irrigationSlot) {
        return irrigationSlotService.addIrrigationSlot(plotId, irrigationSlot);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "/plots/{plotId}/irrigation-slots/{irrigationSlotId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Get the details of an irrigation slot for a plot of land.")
    public IrrigationSlot getIrrigationSlotDetails(@PathVariable Long plotId, @PathVariable Long irrigationSlotId) {
        return irrigationSlotService.findIrrigationSlotByPlotIdAndIrrigationSlotId(plotId, irrigationSlotId);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @PutMapping(value = "/plots/{plotId}/irrigation-slots/{irrigationSlotId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Update the details of an irrigation slot for a plot of land.")
    public IrrigationSlot updateIrrigationSlotDetails(@PathVariable Long plotId, @PathVariable Long irrigationSlotId, @RequestBody IrrigationSlot irrigationSlot) {
        return irrigationSlotService.updateIrrigationSlotByPlotIdAndIrrigationSlotId(plotId, irrigationSlotId, irrigationSlot);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "/plots/{plotId}/irrigation-slots/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Get a list of all irrigation slots for a plot of land.")
    public List<IrrigationSlot> getIrrigationSlotsByPlotId(@PathVariable Long plotId) {
        return irrigationSlotService.getIrrigationSlotsByPlotId(plotId);
    }

    @ApiResponse(responseCode = "200", description = "Success")
    @PostMapping(value = "/plots/{irrigationSlotId}/irrigation-status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Irrigate a plot of land.")
    public APIResponse<String> updateIrrigationStatus(@PathVariable Long irrigationSlotId, @RequestBody String status) {
        return new APIResponseBuilder<String>().code(200).addMessage("OK")
                .body(irrigationSlotService.updateIrrigationStatus(irrigationSlotId, status)).build();
    }
    @ApiResponse(responseCode = "200", description = "Success")
    @GetMapping(value = "/plots/{irrigationSlotId}/irrigation-status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Get the irrigation status of a plot of land.")
    public APIResponse<String> getIrrigationStatus(@PathVariable Long irrigationSlotId) {
        return new APIResponseBuilder<String>().code(200).addMessage("OK")
                .body(irrigationSlotService.getIrrigationStatus(irrigationSlotId)).build();
    }
    @ApiResponse(responseCode = "200", description = "Success")
    @DeleteMapping(value = "/plots/{plotId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Delete the details of an irrigation slot for a plot of land.")
    public APIResponse<String> deletePlot(@PathVariable Long plotId) {
        return new APIResponseBuilder<String>().code(200).addMessage("OK")
                .body(plotService.deletePlotByIPlotId(plotId)).build();
    }
    @ApiResponse(responseCode = "200", description = "Success")
    @DeleteMapping(value = "/plots/{plotId}/irrigation-slots/{irrigationSlotId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "API to Delete the details of an irrigation slot for a plot of land.")
    public APIResponse<String> deleteIrrigationSlotDetails(@PathVariable Long plotId, @PathVariable Long irrigationSlotId) {
        return new APIResponseBuilder<String>().code(200).addMessage("OK")
                .body(irrigationSlotService.deleteIrrigationSlotByPlotIdAndIrrigationSlotId(plotId, irrigationSlotId)).build();
    }
}