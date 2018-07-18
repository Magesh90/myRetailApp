package com.myretailapp.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ProductInformation {

    String name
    int id
    @JsonProperty(value = "current_price")
    Price price

}
