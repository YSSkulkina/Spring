package ru.skulkina.project3RestApp.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skulkina.project3RestApp.DTO.MeasurementDTO;
import ru.skulkina.project3RestApp.models.Measurement;
import ru.skulkina.project3RestApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;

        this.sensorService = sensorService;
    }

    public List<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }
    public int rainyDaysCount() {

        return measurementRepository.countByRainingIsTrue();

    }

    @Transactional
    public void saveMeasurement (Measurement measurement) {
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);

    }
    private void enrichMeasurement(Measurement measurement){
        measurement.setMeasureAt(LocalDateTime.now());
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
    }


}
