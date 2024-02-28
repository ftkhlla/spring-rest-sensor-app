package ru.springcourse.FirstRestApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementDTO {
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;

    @NotNull
    private Boolean raining;

    @NotNull
    private SensorDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
