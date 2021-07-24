CREATE DATABASE test_task;
CREATE SEQUENCE seq_id;

CREATE TABLE sensor_readings
(
    id bigint NOT NULL DEFAULT nextval('seq_id'::regclass),
    object_id int NOT NULL,
    sensor_id int NOT NULL,
    time bigint NOT NULL,
    value double precision NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY (id)
);

COMMENT ON COLUMN sensor_readings.object_id
    IS 'Объект (здание)';

COMMENT ON COLUMN sensor_readings.sensor_id
    IS 'Температурный датчик';

COMMENT ON COLUMN sensor_readings.time
    IS 'Время в секундах';

COMMENT ON COLUMN sensor_readings.value
    IS 'Температура';

COMMENT ON TABLE sensor_readings is 'Данные с сенсоров';


