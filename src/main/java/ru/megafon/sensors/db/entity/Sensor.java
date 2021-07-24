package ru.megafon.sensors.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sensor_readings")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "object_id")
    @NonNull
    private Integer objectId;

    @Column(name = "sensor_id")
    @NonNull
    private Integer sensorId;

    @Column(name = "time")
    @NonNull
    private Long time;

    @Column(name = "value")
    @NonNull
    private Double value;


    public Sensor(@NonNull Integer objectId, @NonNull Integer sensorId, @NonNull Long time, @NonNull Double value) {
        this.objectId = objectId;
        this.sensorId = sensorId;
        this.time = time;
        this.value = value;
    }
}

