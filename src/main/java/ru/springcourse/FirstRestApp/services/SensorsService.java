package ru.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springcourse.FirstRestApp.models.Sensor;
import ru.springcourse.FirstRestApp.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorRepository sensorRepository;
    @Autowired
    public SensorsService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Sensor findOne(int id){
        return sensorRepository.findById(id).orElse(null);
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }
    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public boolean checkUnique(Sensor sensor){
        return sensorRepository.existsByName(sensor.getName());

    }
}
