package ru.megafon.sensors.controler;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.megafon.sensors.model.RequestLatest;
import ru.megafon.sensors.db.entity.Sensor;
import ru.megafon.sensors.service.SensorService;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class SensorControler {

    private final SensorService service;

    @Autowired
    public SensorControler(SensorService service) {
        this.service = service;
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "сохранение списка данных с сенсоров")
    public String saveSensors(@RequestBody List<Sensor> sensors) {
        sensors.forEach(service::save);
        return "Success";
    }

    @GetMapping(value = "/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "получить текущие значения всех датчиков для заданного объекта")
    @ResponseBody
    List<RequestLatest> getLatest(@RequestParam(name = "objectId") Integer objectId) {
        return service.getLatestTemperatureObjectId(objectId);
    }

    @GetMapping(value = "/avg", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "получить среднее из текущих значений датчиков для каждого объекта")
    @ResponseBody
    Double getAvg(@RequestParam(name = "objectId") Integer objectId) {
        return service.getAverageTemperature(objectId);
    }

    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "получить все измерения заданного датчика за заданный интервал времени")
    @ResponseBody
    List<Double> getHistory(@RequestParam(name = "objectId") Integer objectId,
                      @RequestParam(name = "sensorId") Integer sensorId,
                      @RequestParam(name = "startTime") Long startTime,
                      @RequestParam(name = "endTime") Long endTime) {
        return service.getHistory(objectId, sensorId, startTime, endTime);
    }

}
