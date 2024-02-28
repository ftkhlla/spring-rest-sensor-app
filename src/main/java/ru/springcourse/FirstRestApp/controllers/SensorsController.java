package ru.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.FirstRestApp.dto.SensorDTO;
import ru.springcourse.FirstRestApp.models.Sensor;
import ru.springcourse.FirstRestApp.models.SensorResponse;
import ru.springcourse.FirstRestApp.services.SensorsService;
import ru.springcourse.FirstRestApp.util.SensorErrorResponse;
import ru.springcourse.FirstRestApp.util.SensorNotCreatedException;
import ru.springcourse.FirstRestApp.util.SensorValidator;
import ru.springcourse.FirstRestApp.util.ErrorUtil;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensor")
public class SensorsController {
    Logger logger = LoggerFactory.getLogger(SensorsController.class);
    private final SensorsService sensorsService;

    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping()
    public SensorResponse getAllSensor() {
        return new SensorResponse(sensorsService.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public SensorDTO getSensorById(@PathVariable("id") int id) {
        return convertToSensorDTO(sensorsService.findOne(id));
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        Sensor sensorToBeRegistered = convertToSensor(sensorDTO);
        //TODO
//       sensorValidator.validate(sensorToBeRegistered,bindingResult);
        if(sensorsService.findByName(sensorToBeRegistered.getName()).isPresent()){
            logger.info("Logger check");
            throw new SensorNotCreatedException("Sensor already registered");
        }
        if (bindingResult.hasErrors()) {
            ErrorUtil.returnErrorsToClient(bindingResult);
        }
        sensorsService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        sensor.setName(sensorDTO.getName()); // Set the name field
        return sensor;
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





}
