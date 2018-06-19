package com.myretailapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    Item item;

    public Item getItem() {
        return item;
    }
}
