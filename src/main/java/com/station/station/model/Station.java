package com.station.station.model;

import com.station.station.model.enums.System;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Station {
    private int id_station;
    private String station_name;
    private String location;
    private System system;
}
