package com.myretailapp.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class Item {

    @JsonProperty(value = "product_description")
    ProductDescription productDescription

}
