package com.myretailapp.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "price")
class Price {

    @Id
    String identity

    int id

    Double value

    @JsonProperty(value = "currency_code")
    private String currenyCode

    @JsonIgnore
    int getId() {
         id
    }

    @JsonProperty(value = "id")
    public void setId(int id) {
        this.id = id
    }

}
