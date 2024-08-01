package ru.skulkina.project3RestApp.controllers;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.skulkina.project3RestApp.DTO.MeasurementDTO;
import ru.skulkina.project3RestApp.DTO.SensorDTO;
import ru.skulkina.project3RestApp.models.Measurement;
import ru.skulkina.project3RestApp.models.Sensor;
import ru.skulkina.project3RestApp.repositories.MeasurementRepository;
import ru.skulkina.project3RestApp.services.MeasurementService;
import ru.skulkina.project3RestApp.util.MeasurementErrorResponce;
import ru.skulkina.project3RestApp.util.MeasurementException;
import ru.skulkina.project3RestApp.util.MeasurementValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.skulkina.project3RestApp.util.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;


    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurement() {
        return measurementService.getMeasurements().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    private int getRainyDaysCount() {
        return measurementService.rainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                BindingResult bindingResult){
       Measurement measurementToAdd=convertToMeasurement(measurementDTO);
       measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }
        measurementService.saveMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponce> handleException(MeasurementException e){
        MeasurementErrorResponce responce=new MeasurementErrorResponce(
                e.getMessage(),System.currentTimeMillis()
        );
        return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }
    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
