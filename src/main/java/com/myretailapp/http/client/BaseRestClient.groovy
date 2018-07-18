package com.myretailapp.http.client

import com.myretailapp.exception.MyRetailAppTimeoutException
import com.myretailapp.exception.UnExpectedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.client.HttpStatusCodeException
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate

import java.util.Map
import java.util.concurrent.TimeoutException

abstract class BaseRestClient {

    @Autowired
    RestTemplate restTemplate

    abstract String getBaseUri()

    Object exchange(HttpMethod httpMethod, String serviceUrl, Map<String, String> uriParams, Object requestBody, Class responseClassType) {
        ResponseEntity responseEntity = null
        try {
            responseEntity = restTemplate.exchange(buildUri(getBaseUri(), serviceUrl), httpMethod, buildHttpEntity(requestBody), responseClassType, uriParams)
        } catch (HttpStatusCodeException ex) {
            if (ex.getCause() instanceof TimeoutException) {
                throw new MyRetailAppTimeoutException(ex.getMessage())
            }
        } catch (ResourceAccessException ex) {
            throw new MyRetailAppTimeoutException(ex.getMessage())
        } catch (Exception ex) {
            throw new UnExpectedException("Unexpected exception happened")
        }
        if (responseEntity.getStatusCode().value() != 200) {
            throw new UnExpectedException("Unexpected exception happened while handling the request")
        }
        responseEntity.getBody()
    }

    String buildUri(String baseUri, String serviceUrl) {
        baseUri + serviceUrl
    }

    HttpEntity buildHttpEntity(Object requestBody) {
        HttpEntity httpEntity
        HttpHeaders httpHeaders = new HttpHeaders()
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        if (requestBody != null) {
            httpEntity = new HttpEntity<>(requestBody, httpHeaders)
        } else {
            httpEntity = new HttpEntity<>(httpHeaders)
        }
        httpEntity
    }
}