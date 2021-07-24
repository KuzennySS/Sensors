package ru.megafon.sensors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.megafon.sensors.db.entity.RequestLatest;
import ru.megafon.sensors.db.entity.Sensor;
import ru.megafon.sensors.db.repository.SensorRepository;

import java.util.List;

@Service
public class SensorService {

    private final SensorRepository repository;

    @Autowired
    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }

    public Sensor save(Sensor sensor){
        return repository.save(sensor);
    }

    public List<RequestLatest> getLatestTemperatureObjectId(int objectId){
        return repository.getRequestLatestByObjectId(objectId);
    }

    public Double getAverageTemperature(int objectId){
        return repository.getAverageTemperature(objectId);
    }

    public List<Double> getHistory(int objectId, int sensorId, long startTime, long endTime){
        return repository.getHistory(objectId, sensorId, startTime, endTime);
    }

    public List<Sensor> getByObjectIdAndAndSensorId(int objectId, int sensorId){
        return repository.getByObjectIdAndAndSensorId(objectId, sensorId);
    }
}
