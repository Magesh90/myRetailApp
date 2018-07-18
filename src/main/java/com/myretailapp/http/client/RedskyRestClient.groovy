package com.myretailapp.http.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class RedskyRestClient extends BaseRestClient {

    @Value('${redskyrestclient.uri}')
    private String redskyRestClientUri

    @Override
    String getBaseUri() {
         redskyRestClientUri
    }

}