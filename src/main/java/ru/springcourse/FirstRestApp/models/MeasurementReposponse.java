package ru.springcourse.FirstRestApp.models;

import ru.springcourse.FirstRestApp.dto.MeasurementDTO;

import java.util.List;

public class MeasurementReposponse {
    private List<MeasurementDTO> measurements;

    public MeasurementReposponse(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
