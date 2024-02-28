package ru.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springcourse.FirstRestApp.models.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    int countByRainingIsTrue ();
}
