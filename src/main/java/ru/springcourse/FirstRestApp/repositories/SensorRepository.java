package ru.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springcourse.FirstRestApp.models.Sensor;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    boolean existsByName(String name);
    Optional<Sensor> findByName(String name);
}
