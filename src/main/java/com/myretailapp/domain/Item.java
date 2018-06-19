package com.myretailapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    @JsonProperty(value = "product_description")
    ProductDescription productDescription;

    public ProductDescription getProductDescription() {
        return productDescription;
    }
}
