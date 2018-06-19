package com.myretailapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDescription {

    String title;

    public String getTitle() {
        return title;
    }
}
