package ru.skulkina.project3RestApp.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JavaClient {
    public static void main(String[] args) {
        String sensorName="Sensor"+new Random().nextInt(10);
        registerSensor(sensorName);

        //Добавляем измерения
        for (int i = 0; i<1000; i++){
        Random random = new Random();
        Double randomValue= random.nextDouble(-100,100);
        Boolean randomRaining= random.nextBoolean();
        addMeasurement(randomValue,randomRaining,sensorName);


    }}
    public static void registerSensor(String sensorName){
        String url="http://localhost:8080/sensors/registration";
        Map<String,Object> jsonToSend = new HashMap<>();
        jsonToSend.put("name",sensorName);
        makePostRequestWithJSONData(url,jsonToSend);

    }
    public static void addMeasurement( Double value,Boolean raining,String sensorName){
        String url="http://localhost:8080//measurements/add";
        Map<String,Object> jsonToSend = new HashMap<>();
        jsonToSend.put("value",value);
        jsonToSend.put("raining",raining);
        jsonToSend.put("sensorName",sensorName);
        makePostRequestWithJSONData(url,jsonToSend);
    }



    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try {
            restTemplate.postForObject(url, request, String.class);

            System.out.println("Измерение успешно отправлено на сервер!");
        } catch (HttpClientErrorException e) {
            System.out.println("ОШИБКА!");
            System.out.println(e.getMessage());
        }

    }
}
