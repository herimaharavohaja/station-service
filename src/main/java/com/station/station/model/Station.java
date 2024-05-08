package com.station.station.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Station {
    private String stationName;
    private String location;
    private String system;
}
