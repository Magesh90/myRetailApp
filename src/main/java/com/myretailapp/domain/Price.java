package com.myretailapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "price")
public class Price {

    @Id
    private String identity;

    private int id;

    private Double value;

    @JsonProperty(value = "currency_code")
    private String currenyCode;

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonProperty(value = "id")
    public void setId(int id) {
        this.id = id;
    }


    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCurrenyCode() {
        return currenyCode;
    }

    public void setCurrenyCode(String currenyCode) {
        this.currenyCode = currenyCode;
    }
}
