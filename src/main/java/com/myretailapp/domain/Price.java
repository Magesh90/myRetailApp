package com.myretailapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Price {

    Double value;

    @JsonProperty(value = "currency_code")
    String currenyCode;
}
