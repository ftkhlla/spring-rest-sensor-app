package ru.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.FirstRestApp.dto.MeasurementDTO;
import ru.springcourse.FirstRestApp.models.Measurement;
import ru.springcourse.FirstRestApp.models.MeasurementReposponse;
import ru.springcourse.FirstRestApp.services.MeasurementService;
import ru.springcourse.FirstRestApp.services.SensorsService;
import ru.springcourse.FirstRestApp.util.SensorErrorResponse;
import ru.springcourse.FirstRestApp.util.SensorNotCreatedException;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static ru.springcourse.FirstRestApp.util.ErrorUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, SensorsService sensorsService) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.sensorsService = sensorsService;
    }

    @GetMapping()
    public MeasurementReposponse getMeasurements() {

        return new MeasurementReposponse(measurementService.findAll().stream().map(this::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        Measurement measurement = convertToMeasurement(measurementDTO);

        if (!sensorsService.findByName(measurement.getSensor().getName()).isPresent()) {
            throw new SensorNotCreatedException("Sensor does not registered");
        }
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }


    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handler(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
