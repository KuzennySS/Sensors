package ru.megafon.sensors;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.megafon.sensors.db.entity.RequestLatest;
import ru.megafon.sensors.db.entity.Sensor;
import ru.megafon.sensors.service.SensorService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  Класс для тестирования методов класса SensorService
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SensorServiceTest {

    private Sensor sensor9;
    private Sensor sensor92;
    private Sensor sensor10;
    private static final int objectId = 9;
    private static final int sensorId9 = 9;
    private static final int sensorId10 = 10;
    private static final long time9 = 1234567890L;
    private static final long time92 = 2234567890L;
    private static final double temperature9 = 9.4;
    private static final double temperature92 = 15.6;
    private static final double temperature10 = 14.7;

    @Autowired
    private SensorService service;

    @Before
    public void init(){
        sensor9 = new Sensor(objectId, sensorId9, time9, temperature9);
        sensor92 = new Sensor(objectId, sensorId9, time92, temperature92);
        sensor10 = new Sensor(objectId, sensorId10, 1234567890L, temperature10);
        service.save(sensor10);
        service.save(sensor9);
        service.save(sensor92);
    }

    @Test
    public void saveSensorTest() {
        // when
        List<Sensor> sensorDBs = service.getByObjectIdAndAndSensorId(objectId, sensorId9);

        // then
        Assertions.assertTrue(sensorDBs.contains(sensor9));
    }

    @Test
    public void getLatestTemperatureObjectIdTest() {
        // before
        List<RequestLatest> streamList = Stream.of(sensor9, sensor10, sensor92)
                .map(sensor -> new RequestLatest(sensor.getSensorId(), sensor.getValue())).sorted().collect(Collectors.toList());

        // when
        List<RequestLatest> latestListDB = service.getLatestTemperatureObjectId(objectId);
        Collections.sort(latestListDB);

        // then
        Assertions.assertEquals(streamList, latestListDB);
    }

    @Test
    public void getAverageTemperatureTest() {
        // before
        double avrTemperature = (temperature9 + temperature10 + temperature92) / 3;

        // when
        Double temperatureDB = service.getAverageTemperature(objectId);

        // then
        Assertions.assertEquals(avrTemperature, temperatureDB);
    }

    @Test
    public void getHistoryTwoTest() {
        // before
        List<Double> temperatures = List.of(temperature9, temperature92);

        // when
        List<Double> temperaturesDB  = service.getHistory(objectId, sensorId9, time9, time92);

        // then
        Assertions.assertEquals(temperatures, temperaturesDB);
    }

    @Test
    public void getHistoryOneTest() {
        // before
        List<Double> temperatures = List.of(temperature9);

        // when
        List<Double> temperaturesDB  = service.getHistory(objectId, sensorId9, time9, time92 - 1);

        // then
        Assertions.assertEquals(temperatures, temperaturesDB);
    }

}
