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
public class LoginSuccessData implements Serializable {
    private long evaluateTime;
    private String applicationName;
    private String userIdentify;
    private String loginSequence;
    private String ordernessPassword;
    private String cityName;
    private GeoPoint geoPoint;
    private Double[] inputFeatures;
    private String deviceInformation;
}
