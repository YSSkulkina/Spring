package ru.skulkina.project3RestApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name="measurement")
public class Measurement {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="sensor_name",referencedColumnName = "name")
    private Sensor sensor;

    @NotNull
    @Min(-100)
    @Max(100)
    @Column (name="value")
    private Double value;

    @NotNull
    @Column(name="raining")
    private boolean raining;

    @Column (name="measure_at")
    private LocalDateTime measureAt;

    public Measurement() {}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getValue() {
        return value;
    }

    public void setValue( double value) {
        this.value = value;
    }
    public boolean isRaining() {
        return raining;
    }
    public void setRaining(boolean raining) {
        this.raining = raining;
    }
    public LocalDateTime getMeasureAt() {
        return measureAt;
    }
    public void setMeasureAt(LocalDateTime measureAt) {
        this.measureAt = measureAt;
    }
    public @NotNull Sensor getSensor() {
        return sensor;
    }
    public void setSensor(@NotNull Sensor sensor) {
        this.sensor = sensor;
    }
}
