package ru.skulkina.project3RestApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.skulkina.project3RestApp.DTO.SensorDTO;
import ru.skulkina.project3RestApp.models.Sensor;
import ru.skulkina.project3RestApp.services.SensorService;
import ru.skulkina.project3RestApp.util.MeasurementErrorResponce;
import ru.skulkina.project3RestApp.util.MeasurementException;
import ru.skulkina.project3RestApp.util.SensorValidator;

import static ru.skulkina.project3RestApp.util.ErrorsUtil.returnErrorsToClient;


@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;


    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;

        this.sensorValidator = sensorValidator;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                BindingResult bindingResult){
    Sensor sensorToAdd=convertToSensor(sensorDTO);
    sensorValidator.validate(sensorToAdd, bindingResult);
        if (bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }
        sensorService.save(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponce> handleException(MeasurementException e){
        MeasurementErrorResponce responce=new MeasurementErrorResponce(
                e.getMessage(),System.currentTimeMillis()
        );
        return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO){

        return modelMapper.map(sensorDTO, Sensor.class);
    }


}
