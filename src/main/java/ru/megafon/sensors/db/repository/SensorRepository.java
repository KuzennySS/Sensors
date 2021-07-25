package ru.megafon.sensors.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.megafon.sensors.model.RequestLatest;
import ru.megafon.sensors.db.entity.Sensor;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    @Query("SELECT NEW ru.megafon.sensors.model.RequestLatest(s.sensorId, s.value) " +
            "FROM Sensor s WHERE s.objectId = :objectId")
    List<RequestLatest> getRequestLatestByObjectId(int objectId);

    @Query("SELECT avg(s.value) FROM Sensor s WHERE s.objectId = :objectId")
    Double getAverageTemperature(int objectId);

    @Query("SELECT s.value FROM Sensor s WHERE s.objectId = :objectId AND s.sensorId = :sensorId " +
            "AND s.time BETWEEN :startTime AND :endTime")
    List<Double> getHistory(int objectId, int sensorId, long startTime, long endTime);

    List<Sensor> getByObjectIdAndAndSensorId(int objectId, int sensorId);

}