package com.pml.cloud.utils.evaluate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class GeoPoint implements Serializable {
    private double longtitude;//经度
    private double latitude;//纬度
}
