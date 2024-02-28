package ru.springcourse.FirstRestApp.models;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @NotNull(message = "Value should not be empty")
    @Range(min = -100, max = 100, message = "Value should be in the range -100 to 100")
    private double value;


    @NotNull(message = "Raining should not be empty")
    @Column(name = "raining")
    private boolean raining;

    @Column(name = "measurement_date_time")
    @NotNull
    private LocalDateTime measurementDateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name="sensor", referencedColumnName = "name")
    private Sensor sensor;


    public Measurement(int id, double value, boolean raining, LocalDateTime measurementDateTime, Sensor sensor) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.measurementDateTime = measurementDateTime;
        this.sensor = sensor;
    }


    public Measurement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getMeasurementDateTime() {
        return measurementDateTime;
    }

    public void setMeasurementDateTime(LocalDateTime measurementDateTime) {
        this.measurementDateTime = measurementDateTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
