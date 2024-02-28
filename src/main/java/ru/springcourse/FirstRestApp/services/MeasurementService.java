package ru.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springcourse.FirstRestApp.models.Measurement;
import ru.springcourse.FirstRestApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorsService sensorsService) {
        this.measurementRepository = measurementRepository;
        this.sensorsService = sensorsService;
    }

    public List<Measurement> findAll(){
        return  measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement){
        measurement.setSensor(sensorsService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurementDateTime(LocalDateTime.now());
        measurementRepository.save(measurement);
    }



    public int getRainyDaysCount(){
        return measurementRepository.countByRainingIsTrue();
    }
}
