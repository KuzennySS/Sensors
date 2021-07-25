package ru.megafon.sensors.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestLatest implements Comparable<RequestLatest>{

    private Integer sensorId;

    private Double value;

    @Override
    public int compareTo(RequestLatest r) {
        return this.value.compareTo(r.value);
    }
}
